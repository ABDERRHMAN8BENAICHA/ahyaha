package com.example.ahyaha.data.repository // Or wherever DonorRepositoryImpl is

import com.example.ahyaha.data.model.Donor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


@Singleton
class DonorRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : DonorRepository {

    private val donorsCollection = firestore.collection("donors")

    override fun getDonors(): Flow<Result<List<Donor>>> {
        return donorsCollection
            .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .snapshots()
            .map { snapshot: QuerySnapshot ->

                val donors = snapshot.documents.mapNotNull { doc ->
                    try {
                        doc.toObject<Donor>()
                    } catch (e: Exception) {

                        println("Error converting document ${doc.id}: ${e.message}")
                        null
                    }
                }
                Result.Success(donors) as Result<List<Donor>>
            }
            .catch { exception ->
                println("Error fetching donors: ${exception.message}")
                emit(Result.Error(Exception("Failed to fetch donors", exception)))
            }
    }

    override fun getDonorById(donorId: String): Flow<Result<Donor?>> {
        if (donorId.isBlank()) {
            return flow { emit(Result.Error(IllegalArgumentException("Donor ID cannot be blank"))) }
        }
        return firestore.collection("donors").document(donorId)
            .snapshots()
            .map { snapshot ->
                try {
                    val donor = snapshot.toObject<Donor>()
                    if (snapshot.exists()) {
                        Result.Success(donor) as Result<Donor?>
                    } else {
                        Result.Success(null)
                    }
                } catch (e: Exception) {
                    println("Error converting document ${snapshot.id}: ${e.message}")
                    Result.Error(e) as Result<Donor?>
                }
            }
            .catch { exception ->
                println("Error fetching donor $donorId: ${exception.message}")
                emit(Result.Error(Exception("Failed to fetch donor $donorId", exception)))
            }
            .flowOn(Dispatchers.IO)
    }


    override suspend fun addDonor(donor: Donor): Result<String> {
        return try {
            val documentRef = donorsCollection.add(donor).await()
            Result.Success(documentRef.id)
        } catch (e: Exception) {
            println("Error adding donor: ${e.message}")
            Result.Error(Exception("Failed to add donor", e))
        }
    }

    override suspend fun updateDonor(donor: Donor): Result<Unit> {
        if (donor.id.isBlank()) {
            return Result.Error(Exception("Donor ID is missing for update"))
        }
        return try {
            val updates = mapOf(
                "name" to donor.name,
                "email" to donor.email,
                "phoneNumber" to donor.phoneNumber,
                "profilePicture" to donor.profilePicture,
                "bloodGroup" to donor.bloodGroup,
                "Rh" to donor.rh,
                "location" to donor.location,
                "lastDonationDate" to donor.lastDonationDate, // Already a Timestamp
                "updatedAt" to FieldValue.serverTimestamp() // Special value for server time
            )
            donorsCollection.document(donor.id).update(updates).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            println("Error updating donor ${donor.id}: ${e.message}")
            Result.Error(Exception("Failed to update donor", e))
        }
    }


    override suspend fun deleteDonor(donorId: String): Result<Unit> {
        if (donorId.isBlank()) {
            return Result.Error(Exception("Donor ID is missing for delete"))
        }
        return try {
            donorsCollection.document(donorId).delete().await()
            Result.Success(Unit)
        } catch (e: Exception) {
            println("Error deleting donor $donorId: ${e.message}")
            Result.Error(Exception("Failed to delete donor", e))
        }
    }
}