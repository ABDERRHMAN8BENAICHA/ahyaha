package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDonorsUseCase @Inject constructor(private val donorRepository: DonorRepository) {

    operator fun invoke(): Flow<Result<List<Donor>>> {
        return donorRepository.getDonors()
    }
}