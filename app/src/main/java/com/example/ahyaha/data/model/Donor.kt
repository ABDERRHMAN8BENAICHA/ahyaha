package com.example.ahyaha.data.model

import java.util.Date

data class Donor(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val profilePicture: String?,
    val bloodGroup: String,
    val Rh: String,
    val location: String,
    val lastDonationDate: Date?,
    val createdAt: Date,
    val updatedAt: Date
)
