
package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.data.repository.DonorRepository
import com.example.ahyaha.data.repository.Result
import javax.inject.Inject

class UpdateDonorUseCase @Inject constructor(private val repository: DonorRepository) {
    suspend operator fun invoke(donor: Donor): Result<Unit> = repository.updateDonor(donor)
}