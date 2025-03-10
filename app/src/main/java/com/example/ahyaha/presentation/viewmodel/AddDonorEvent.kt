package com.example.ahyaha.presentation.viewmodel



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