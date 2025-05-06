package com.example.ahyaha.presentation.viewmodel

import android.net.Uri
import com.example.ahyaha.data.model.Donor

data class DonorDetailState(
    // Data loading state
    val isLoading: Boolean = true,
    val donor: Donor? = null,
    val error: String? = null,

    // UI mode state
    val isEditing: Boolean = false,

    // Fields to hold temporary edits (sync with Donor model)
    val editedName: String = "",
    val editedEmail: String = "",
    val editedPhoneNumber: String = "",
    val editedBloodGroup: String = "",
    val editedRh: String = "",
    val editedLocation: String = "",
    val editedProfilePictureUri: Uri? = null,

    // Operation states
    val isSaving: Boolean = false,
    val saveError: String? = null,
    val saveSuccess: Boolean = false,
    val isDeleting: Boolean = false,
    val deleteError: String? = null,
    val deleteSuccess: Boolean = false
)