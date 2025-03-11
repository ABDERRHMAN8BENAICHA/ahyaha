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
import javax.inject.Inject


data class AddDonorState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val rh: String = "",
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
class AddDonorViewModel @Inject constructor(private val addDonorUseCase: AddDonorUseCase) : ViewModel() {

    //TODO: Add Donor logic
    private val _state = MutableStateFlow(AddDonorState())
    val state: StateFlow<AddDonorState> = _state

    //event handeling fun
    fun onEvent(event : AddDonorEvent){
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
                _state.update { it.copy(rh = event.rh) }
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
        if (validateInput()) {
            _state.update { it.copy(isLoading = true, error = null) }

            viewModelScope.launch {
                try {
                    val donor = Donor(
                        name = state.value.name,
                        email = state.value.email,
                        phoneNumber = state.value.phoneNumber,
                        bloodGroup = state.value.bloodGroup,
                        Rh = state.value.rh,
                        location = state.value.location,
                        profilePicture = state.value.profilePicture
                    )
                    addDonorUseCase(donor)
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                } catch (e: Exception) {
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
            }
        } else {
            _state.update { it.copy(error = "Please fill all fields correctly") }
        }
    }

    // Input validation
    private fun validateInput(): Boolean {
        return state.value.name.isNotBlank() &&
                state.value.email.isNotBlank() &&
                state.value.phoneNumber.isNotBlank() &&
                state.value.bloodGroup.isNotBlank() &&
                state.value.rh.isNotBlank() &&
                state.value.location.isNotBlank()
    }

}