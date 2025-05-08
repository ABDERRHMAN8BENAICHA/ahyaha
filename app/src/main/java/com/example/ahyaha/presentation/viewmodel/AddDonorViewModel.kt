package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.domain.usecase.AddDonorUseCase
import com.example.ahyaha.data.model.Donor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import com.example.ahyaha.presentation.viewmodel.AddDonorEvent
import com.example.ahyaha.presentation.viewmodel.AddDonorState



@HiltViewModel
class AddDonorViewModel @Inject constructor(
    private val addDonorUseCase: AddDonorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddDonorState())
    val state: StateFlow<AddDonorState> = _state

    fun onEvent(event: AddDonorEvent) {
        when (event) {
            is AddDonorEvent.NameChanged -> {
                _state.value = _state.value.copy(name = event.value)
            }
            is AddDonorEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.value)
            }
            is AddDonorEvent.PhoneNumberChanged -> {
                _state.value = _state.value.copy(phoneNumber = event.value)
            }
            is AddDonorEvent.BloodGroupChanged -> {
                _state.value = _state.value.copy(bloodGroup = event.value)
            }
            is AddDonorEvent.RhChanged -> {
                _state.value = _state.value.copy(rh = event.value)
            }
            is AddDonorEvent.LocationChanged -> {
                _state.value = _state.value.copy(location = event.value)
            }
            is AddDonorEvent.ProfilePictureChanged -> {
                _state.value = _state.value.copy(profilePicture = event.value)
            }
            is AddDonorEvent.Submit -> {
                submitDonor()
            }
        }
    }
    private fun submitDonor() {
        if (_state.value.isLoading) return // Prevent double submission

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {

                _state.value = _state.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = mapOf("general" to e.localizedMessage.orEmpty())
                )
            }
        }
    }





}