package com.example.ahyaha.presentation.viewmodel

sealed class AddDonorEvent {
    data class NameChanged(val value: String) : AddDonorEvent()
    data class EmailChanged(val value: String) : AddDonorEvent()
    data class PhoneNumberChanged(val value: String) : AddDonorEvent()
    data class BloodGroupChanged(val value: String) : AddDonorEvent()
    data class RhChanged(val value: String) : AddDonorEvent()
    data class LocationChanged(val value: String) : AddDonorEvent()
    data class ProfilePictureChanged(val value: String) : AddDonorEvent()
    object Submit : AddDonorEvent()
}