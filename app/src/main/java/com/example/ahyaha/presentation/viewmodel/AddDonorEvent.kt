

package com.example.ahyaha.presentation.viewmodel
import android.net.Uri



sealed class AddDonorEvent {
    data class NameChanged(val name: String) : AddDonorEvent()
    data class EmailChanged(val email: String) : AddDonorEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : AddDonorEvent()
    data class BloodGroupChanged(val bloodGroup: String) : AddDonorEvent()
    data class RhChanged(val rh: String) : AddDonorEvent()
    data class LocationChanged(val location: String) : AddDonorEvent()
    data class ProfilePicture(val uri: Uri?) : AddDonorEvent()
    object Submit : AddDonorEvent()
}