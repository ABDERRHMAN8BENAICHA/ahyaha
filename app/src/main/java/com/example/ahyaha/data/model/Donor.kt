package com.example.ahyaha.data.model

import java.util.Date
import com.google.firebase.firestore.PropertyName
data class Donor(
    val id: String = "",
    val name: String? = null,
    val phoneNumber: String? = null,
    val bloodGroup: String? = null,
    val location: String? = null,
    val rh: String? = null,
    val lastDonationDate: String? = null,
    val email: String? = null,
    val IDcreature: String? = null,
    val profilePicture: String? = null,
    @PropertyName("created_at")
    val createdAt: Date? = null,
    @PropertyName("updated_at")
    val updatedAt: Date? = null
)
