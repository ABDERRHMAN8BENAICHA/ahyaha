package com.example.ahyaha.data.model

import java.util.Date
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class Donor(
    @DocumentId val id: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val profilePicture: String? = null,
    val bloodGroup: String = "",
    val rh: String = "",
    val location: String = "",
    val lastDonationDate: Timestamp? = null,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

