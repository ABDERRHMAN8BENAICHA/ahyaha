

package com.example.ahyaha.presentation.viewmodel

import android.net.Uri

data class AddDonorState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val rh: String = "",
    val location: String = "",
    val profilePicture: String = "",
    val profilePictureUri   : Uri? = null,
    val isLoading: Boolean = false,
    val error: Map<String, String>? = emptyMap(),// تصحيح نوع البيانات إلى خريطة
    val isSuccess: Boolean = false

)
