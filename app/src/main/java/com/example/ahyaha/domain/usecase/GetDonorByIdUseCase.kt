package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.repository.DonorRepository
import javax.inject.Inject

class GetDonorByIdUseCase @Inject constructor(private val repository: DonorRepository) {
    operator fun invoke(donorId: String) = repository.getDonorById(donorId)
}
