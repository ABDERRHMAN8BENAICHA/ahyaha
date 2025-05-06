package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.Result


import javax.inject.Inject

class AddDonorUseCase @Inject constructor(private val donorRepository: DonorRepository) {

    suspend operator fun invoke(donor: Donor): Result<String> {
        return donorRepository.addDonor(donor)
    }
}

