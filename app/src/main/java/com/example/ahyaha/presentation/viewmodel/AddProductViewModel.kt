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

@HiltViewModel
class AddDonorViewModel @Inject constructor(
    private val addDonorUseCase: AddDonorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddDonorState())
    val state: StateFlow<AddDonorState> = _state

    fun onEvent(event: AddDonorEvent) {
        when (event) {
            is AddDonorEvent.NameChanged -> {
                _state.value = _state.value.copy(name = event.name)
            }
            is AddDonorEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is AddDonorEvent.PhoneNumberChanged -> {
                _state.value = _state.value.copy(phoneNumber = event.phoneNumber)
            }
            is AddDonorEvent.BloodGroupChanged -> {
                _state.value = _state.value.copy(bloodGroup = event.bloodGroup)
            }
            is AddDonorEvent.RhChanged -> {
                _state.value = _state.value.copy(rh = event.rh)
            }
            is AddDonorEvent.LocationChanged -> {
                _state.value = _state.value.copy(location = event.location)
            }
            is AddDonorEvent.ProfilePictureChanged -> {
                _state.value = _state.value.copy(profilePicture = event.url)
            }
            is AddDonorEvent.Submit -> {
                submitDonor()
            }
        }
    }
    private fun submitDonor() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                val donor = Donor(
                    id = generateUniqueId(), // ✅ استخدام دالة إنشاء ID فريد
                    name = _state.value.name,
                    email = _state.value.email,
                    phoneNumber = _state.value.phoneNumber,
                    bloodGroup = _state.value.bloodGroup,
                    Rh = _state.value.rh,
                    location = _state.value.location,
                    profilePicture = _state.value.profilePicture,
                    lastDonationDate = null, // ✅ يمكن أن يكون null
                    createdAt = Date(), // ✅ استخدام التاريخ الحالي
                    updatedAt = Date()  // ✅ استخدام التاريخ الحالي
                )

                addDonorUseCase(donor)
                _state.value = _state.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = mapOf("general" to e.localizedMessage.orEmpty())
                )
            }
        }
    }

    // ✅ دالة توليد ID فريد
    private fun generateUniqueId(): String {
        return java.util.UUID.randomUUID().toString()
    }




}