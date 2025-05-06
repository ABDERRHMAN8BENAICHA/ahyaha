package com.example.ahyaha.presentation.viewmodel



import com.example.ahyaha.data.repository.Result.Success
import com.example.ahyaha.data.repository.Result.Error
import com.example.ahyaha.data.repository.Result.Loading


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.domain.usecase.AddDonorUseCase
import com.example.ahyaha.data.model.Donor

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class   AddDonorViewModel @Inject constructor(
    private val addDonorUseCase: AddDonorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddDonorState())
    val state: StateFlow<AddDonorState> = _state.asStateFlow()

    fun onEvent(event: AddDonorEvent) {
        when (event) {
            // Set error = null when clearing
            is AddDonorEvent.NameChanged -> _state.update { it.copy(name = event.name, error = null, isSuccess = false) }
            is AddDonorEvent.EmailChanged -> _state.update { it.copy(email = event.email, error = null, isSuccess = false) }
            is AddDonorEvent.PhoneNumberChanged -> _state.update { it.copy(phoneNumber = event.phoneNumber, error = null, isSuccess = false) }
            is AddDonorEvent.BloodGroupChanged -> _state.update { it.copy(bloodGroup = event.bloodGroup, error = null, isSuccess = false) }
            is AddDonorEvent.RhChanged -> _state.update { it.copy(rh = event.rh, error = null, isSuccess = false) }
            is AddDonorEvent.LocationChanged -> _state.update { it.copy(location = event.location, error = null, isSuccess = false) }
            is AddDonorEvent.ProfilePicture -> _state.update { it.copy(profilePictureUri = event.uri, error = null, isSuccess = false) }
            is AddDonorEvent.Submit -> submitDonor()
        }
    }

    private fun submitDonor() {
        val currentName = _state.value.name
        val currentBloodGroup = _state.value.bloodGroup
        val currentRh = _state.value.rh

        if (currentName.isBlank()) {
            _state.update { it.copy(error = mapOf("general" to "Name cannot be empty")) }
            return
        }
        if (currentBloodGroup.isBlank() || currentRh.isBlank()) {
            _state.update { it.copy(error = mapOf("general" to "Blood group and Rh factor are required")) }
            return
        }

        _state.update { it.copy(isLoading = true, error = null, isSuccess = false) }

        viewModelScope.launch {
            val donor = Donor(
                name = currentName.trim(),
                email = _state.value.email.trim(),
                phoneNumber = _state.value.phoneNumber.trim(),
                bloodGroup = currentBloodGroup,
                rh = currentRh,
                location = _state.value.location.trim(),
                profilePicture = _state.value.profilePicture,
                lastDonationDate = null,
            )

            val result = addDonorUseCase(donor)


            when (result) {
                is Success<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            error = null
                        )
                    }
                    println("Donor added successfully with ID: ${result.data}")
                }
                is Error -> {
                    val errorMessage = result.exception.message ?: "Failed to add donor"
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = mapOf("general" to errorMessage),
                            isSuccess = false
                        )
                    }
                    println("Error adding donor: ${result.exception.message}")
                }
                is Loading -> {
                    _state.update { it.copy(isLoading = true, error = null, isSuccess = false) }
                }
            }
        }
    }
    fun resetStateStatus() {
        _state.update { it.copy(isSuccess = false, error = null) }
    }
}