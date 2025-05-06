package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.Result

// Remove import com.example.ahyaha.presentation.viewmodel.AddDonorState if not used
import javax.inject.Inject

class UpdateDonorUseCase @Inject constructor(private val donorRepository: DonorRepository) {

    // Invoke is now a suspend function matching the repository
    suspend operator fun invoke(donor: Donor): Result<String> {
        // Add any specific business logic here if needed before calling repo
        return donorRepository.addDonor(donor)
    }
}

