package com.example.ahyaha.data.repository

import com.example.ahyaha.data.model.Donor
import kotlinx.coroutines.flow.Flow

interface DonorRepository {

    fun getDonors(): Flow<Result<List<Donor>>>

    fun getDonorById(donorId: String): Flow<Result<Donor?>>

    suspend fun addDonor(donor: Donor): Result<String>

    suspend fun updateDonor(donor: Donor): Result<Unit>

    suspend fun deleteDonor(donorId: String): Result<Unit>
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}