package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.repository.DonorRepository
import javax.inject.Inject

class GetDonorsUseCase @Inject constructor(private val donorRepository: DonorRepository) {
    operator fun invoke() = donorRepository.getAllDonors()
}