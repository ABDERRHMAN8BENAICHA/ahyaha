

package com.example.ahyaha.presentation.viewmodel
import android.net.Uri



sealed class DonorDetailEvent {
    data class NameChanged(val name: String) : DonorDetailEvent()
    data class EmailChanged(val email: String) : DonorDetailEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : DonorDetailEvent()
    data class BloodGroupChanged(val bloodGroup: String) : DonorDetailEvent()
    data class RhChanged(val rh: String) : DonorDetailEvent()
    data class LocationChanged(val location: String) : DonorDetailEvent()
    data class ProfilePicture(val uri: Uri?) : DonorDetailEvent()
    object Submit : DonorDetailEvent()
}