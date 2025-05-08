package com.example.ahyaha.presentation.viewmodel

data class AddDonorState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val rh: String = "",
    val location: String = "",
    val profilePicture: String = "",
    val isLoading: Boolean = false,
    val error: Map<String, String>? = emptyMap(),
    val isSuccess: Boolean = false
)
