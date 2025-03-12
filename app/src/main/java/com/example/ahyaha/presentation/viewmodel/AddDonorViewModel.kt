package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.domain.usecase.AddDonorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class AddDonorState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val Rh: String = "",
    val location: String = "",
    val profilePicture: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class AddDonorEvent {
    data class NameChanged(val name: String) : AddDonorEvent()
    data class EmailChanged(val email: String) : AddDonorEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : AddDonorEvent()
    data class BloodGroupChanged(val bloodGroup: String) : AddDonorEvent()
    data class RhChanged(val rh: String) : AddDonorEvent()
    data class LocationChanged(val location: String) : AddDonorEvent()
    data class ProfilePictureChanged(val url: String) : AddDonorEvent()
    object Submit : AddDonorEvent()
}

@HiltViewModel
class AddDonorViewModel @Inject constructor(
    private val addDonorUseCase: AddDonorUseCase
) : ViewModel() {

    // State for the form
    private val _state = MutableStateFlow(AddDonorState())
    val state: StateFlow<AddDonorState> = _state

    // Handle events from the UI
    fun onEvent(event: AddDonorEvent) {
        when (event) {
            is AddDonorEvent.NameChanged -> {
                _state.update { it.copy(name = event.name) }
            }
            is AddDonorEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }
            is AddDonorEvent.PhoneNumberChanged -> {
                _state.update { it.copy(phoneNumber = event.phoneNumber) }
            }
            is AddDonorEvent.BloodGroupChanged -> {
                _state.update { it.copy(bloodGroup = event.bloodGroup) }
            }
            is AddDonorEvent.RhChanged -> {
                _state.update { it.copy(Rh = event.rh) }
            }
            is AddDonorEvent.LocationChanged -> {
                _state.update { it.copy(location = event.location) }
            }
            is AddDonorEvent.ProfilePictureChanged -> {
                _state.update { it.copy(profilePicture = event.url) }
            }
            AddDonorEvent.Submit -> {
                submitDonor()
            }
        }
    }

    private fun submitDonor() {

            _state.update { it.copy(isLoading = true, error = null) }

            viewModelScope.launch {

                val donor = Donor(
                    id = UUID.randomUUID().toString(), // Generate a unique ID
                    name = state.value.name,
                    email = state.value.email,
                    phoneNumber = state.value.phoneNumber,
                    bloodGroup = state.value.bloodGroup,
                    Rh = state.value.Rh,
                    location = state.value.location,
                    profilePicture = state.value.profilePicture
                )
                addDonorUseCase(donor)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        name = "",
                        email = "",
                        phoneNumber = "",
                        bloodGroup = "",
                        Rh = "",
                        location = "",
                        profilePicture = ""
                    )
                }

            }
        }
    }
