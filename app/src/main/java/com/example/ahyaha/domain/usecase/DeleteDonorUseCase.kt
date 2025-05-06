package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.Result
import javax.inject.Inject

class DeleteDonorUseCase @Inject constructor(private val repository: DonorRepository) {
    suspend operator fun invoke(donorId: String): Result<Unit> = repository.deleteDonor(donorId)
}