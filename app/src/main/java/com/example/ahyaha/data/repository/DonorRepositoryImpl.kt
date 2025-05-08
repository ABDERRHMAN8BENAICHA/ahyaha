package com.example.ahyaha.data.repository

import com.example.ahyaha.data.model.Donor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.Date
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class DonorRepositoryImpl(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : DonorRepository {

    private val donorsCollection = firestore.collection("donors")
    private val _donorFlow = MutableSharedFlow<List<Donor>>(replay = 1)

    init {
        // Listen to real-time updates
        donorsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("DonorRepositoryImpl", "Error listening to donors", error)
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                val donors = querySnapshot.documents.mapNotNull { document ->
                    try {
                        document.toObject(Donor::class.java)?.copy(id = document.id)
                    } catch (e: Exception) {
                        Log.e("DonorRepositoryImpl", "Error converting document", e)
                        null
                    }
                }
                _donorFlow.tryEmit(donors)
            }
        }
    }

    override fun getAllDonors(): Flow<List<Donor>> = _donorFlow

    override suspend fun addDonor(donor: Donor) {
        try {
            val currentUser = auth.currentUser
                ?: throw IllegalStateException("No authenticated user found")
            
            // Check if donor already exists for this user
            val existingDonors = donorsCollection
                .whereEqualTo("IDcreature", currentUser.uid)
                .get()
                .await()

            if (!existingDonors.isEmpty) {
                throw IllegalStateException("Donor already exists for this user")
            }

            val donorData = donor.copy(
                createdAt = Date(),
                updatedAt = Date(),
                IDcreature = currentUser.uid
            )
            
            donorsCollection
                .add(donorData)
                .await()
        } catch (e: Exception) {
            Log.e("DonorRepositoryImpl", "Error adding donor", e)
            throw e
        }
    }
}

