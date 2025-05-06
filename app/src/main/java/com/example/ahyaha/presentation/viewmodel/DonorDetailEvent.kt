package com.example.ahyaha.presentation.viewmodel

import android.net.Uri

sealed interface DonorDetailEvent {
    object EnterEditMode : DonorDetailEvent
    object ExitEditMode : DonorDetailEvent
    object SaveChanges : DonorDetailEvent
    object DeleteDonor : DonorDetailEvent // Trigger deletion process
    object ResetStatus : DonorDetailEvent // Reset success/error flags

    // Events for editing fields
    data class EditNameChanged(val name: String) : DonorDetailEvent
    data class EditEmailChanged(val email: String) : DonorDetailEvent
    data class EditPhoneNumberChanged(val phone: String) : DonorDetailEvent
    data class EditBloodGroupChanged(val group: String) : DonorDetailEvent
    data class EditRhChanged(val rh: String) : DonorDetailEvent
    data class EditLocationChanged(val location: String) : DonorDetailEvent
    data class EditProfilePictureChanged(val uri: Uri?) : DonorDetailEvent // When new image picked
}