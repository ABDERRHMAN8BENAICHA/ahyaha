package com.example.ahyaha.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.*
import com.example.ahyaha.data.repository.Result
import com.example.ahyaha.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@HiltViewModel
class DonorDetailViewModel : ViewModel {
    private val getDonorByIdUseCase: GetDonorByIdUseCase
    private val updateDonorUseCase: UpdateDonorUseCase
    private val deleteDonorUseCase: DeleteDonorUseCase

    @Inject
    constructor(
        getDonorByIdUseCase: GetDonorByIdUseCase,
        updateDonorUseCase: UpdateDonorUseCase,
        deleteDonorUseCase: DeleteDonorUseCase,
        savedStateHandle: SavedStateHandle
    ) : super() {
        this.getDonorByIdUseCase = getDonorByIdUseCase
        this.updateDonorUseCase = updateDonorUseCase
        this.deleteDonorUseCase = deleteDonorUseCase
        this.donorId = savedStateHandle.get<String>("donorId") ?: ""
        this._state = MutableStateFlow(DonorDetailState())
        this.state = _state.asStateFlow()
        if (donorId.isNotBlank()) {
            observeDonorDetails()
        } else {
            _state.update { it.copy(isLoading = false, error = "Donor ID not provided") }
        }
    }

    private val donorId: String

    private val _state: MutableStateFlow<DonorDetailState>
    val state: StateFlow<DonorDetailState>


    private fun observeDonorDetails() {
        getDonorByIdUseCase(donorId)
            .onStart { _state.update { it.copy(isLoading = true) } }
            .onEach { result ->
                _state.update { currentState ->
                    when (result) {
                        is Result.Success -> {
                            val loadedDonor = result.data
                            currentState.copy(
                                isLoading = false,
                                donor = loadedDonor,
                                error = if (loadedDonor == null && currentState.error == null) "Donor not found" else currentState.error,
                                editedName = loadedDonor?.name ?: currentState.editedName,
                                editedEmail = loadedDonor?.email ?: currentState.editedEmail,
                                editedPhoneNumber = loadedDonor?.phoneNumber ?: currentState.editedPhoneNumber,
                                editedBloodGroup = loadedDonor?.bloodGroup ?: currentState.editedBloodGroup,
                                editedRh = loadedDonor?.rh ?: currentState.editedRh,
                                editedLocation = loadedDonor?.location ?: currentState.editedLocation,
                            )
                        }
                        is Result.Error -> {
                            currentState.copy(
                                isLoading = false,
                                donor = null,
                                error = result.exception.message ?: "Failed to load donor details"
                            )
                        }
                        is Result.Loading -> {
                            currentState.copy(isLoading = true)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: DonorDetailEvent) {
        when (event) {
            is DonorDetailEvent.EnterEditMode -> enterEditMode()
            is DonorDetailEvent.ExitEditMode -> exitEditMode()
            is DonorDetailEvent.SaveChanges -> saveChanges()
            is DonorDetailEvent.DeleteDonor -> deleteDonor()
            is DonorDetailEvent.ResetStatus -> resetStatusFlags()
            is DonorDetailEvent.EditNameChanged -> _state.update { it.copy(editedName = event.name) }
            is DonorDetailEvent.EditEmailChanged -> _state.update { it.copy(editedEmail = event.email) }
            is DonorDetailEvent.EditPhoneNumberChanged -> _state.update { it.copy(editedPhoneNumber = event.phone) }
            is DonorDetailEvent.EditBloodGroupChanged -> _state.update { it.copy(editedBloodGroup = event.group) }
            is DonorDetailEvent.EditRhChanged -> _state.update { it.copy(editedRh = event.rh) }
            is DonorDetailEvent.EditLocationChanged -> _state.update { it.copy(editedLocation = event.location) }
            is DonorDetailEvent.EditProfilePictureChanged -> _state.update { it.copy(editedProfilePictureUri = event.uri) }
        }
    }


    private fun enterEditMode() {
        val currentDonor = _state.value.donor
        _state.update {
            it.copy(
                isEditing = true,
                editedName = currentDonor?.name ?: it.editedName,
                editedEmail = currentDonor?.email ?: it.editedEmail,
                editedPhoneNumber = currentDonor?.phoneNumber ?: it.editedPhoneNumber,
                editedBloodGroup = currentDonor?.bloodGroup ?: it.editedBloodGroup,
                editedRh = currentDonor?.rh ?: it.editedRh,
                editedLocation = currentDonor?.location ?: it.editedLocation,
                editedProfilePictureUri = null,
                saveSuccess = false, saveError = null, deleteSuccess = false, deleteError = null // Reset flags
            )
        }
    }

    private fun exitEditMode() {
        val originalDonor = _state.value.donor
        _state.update {
            it.copy(
                isEditing = false,
                saveError = null,
                editedProfilePictureUri = null,
                editedName = originalDonor?.name ?: "",
                editedEmail = originalDonor?.email ?: "",
                editedPhoneNumber = originalDonor?.phoneNumber ?: "",
                editedBloodGroup = originalDonor?.bloodGroup ?: "",
                editedRh = originalDonor?.rh ?: "",
                editedLocation = originalDonor?.location ?: "",
            )
        }
    }

    private fun saveChanges() {
        val originalDonor = _state.value.donor ?: return
        val editedState = _state.value

        if (editedState.editedName.isBlank()) {
            _state.update { it.copy(saveError = "Name cannot be empty") }
            return
        }

        _state.update { it.copy(isSaving = true, saveError = null, saveSuccess = false) }

        viewModelScope.launch {
            var finalProfilePicUrl = originalDonor.profilePicture
            val newImageUri = editedState.editedProfilePictureUri

            if (newImageUri != null) {
                finalProfilePicUrl = uploadProfilePicture(newImageUri, originalDonor.id)
                if (finalProfilePicUrl == null) {
                    return@launch
                }
            }

            val updatedDonor = originalDonor.copy(
                name = editedState.editedName.trim(),
                email = editedState.editedEmail.trim(),
                phoneNumber = editedState.editedPhoneNumber.trim(),
                bloodGroup = editedState.editedBloodGroup,
                rh = editedState.editedRh,
                location = editedState.editedLocation.trim(),
                profilePicture = finalProfilePicUrl
            )

            val result = updateDonorUseCase(updatedDonor)

            when (result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isSaving = false,
                            isEditing = false,
                            saveSuccess = true,
                            donor = updatedDonor,
                            editedProfilePictureUri = null
                        )
                    }
                }
                is Result.Error -> {
                    _state.update { it.copy(isSaving = false, saveError = result.exception.message ?: "Failed to save") }
                }
                else -> { _state.update { it.copy(isSaving = false)} }
            }
        }
    }

    private suspend fun uploadProfilePicture(uri: Uri, donorId: String): String? {
        return try {
            val storageRef = FirebaseStorage.getInstance().reference

            val imageRef = storageRef.child("profile_pictures/${donorId}/${System.currentTimeMillis()}.jpg")

            imageRef.putFile(uri).await()

            val downloadUrl = imageRef.downloadUrl.await().toString()
            println("Image uploaded successfully: $downloadUrl")
            downloadUrl
        } catch (e: Exception) {
            println("Error uploading image: ${e.message}")
            _state.update { it.copy(isSaving = false, saveError = "Image upload failed: ${e.message}") }
            null
        }
    }



    private fun deleteDonor() {
        if (donorId.isBlank()) return

        _state.update { it.copy(isDeleting = true, deleteError = null, deleteSuccess = false) }
        viewModelScope.launch {
            val result = deleteDonorUseCase(donorId)

            when (result) {
                is Result.Success -> {
                    _state.update { it.copy(isDeleting = false, deleteSuccess = true) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isDeleting = false, deleteError = result.exception.message ?: "Failed to delete") }
                }
                else -> { _state.update { it.copy(isDeleting = false)} }
            }
        }
    }

    private fun resetStatusFlags() {
        _state.update { it.copy(saveSuccess = false, deleteSuccess = false, saveError = null, deleteError = null) }
    }
}