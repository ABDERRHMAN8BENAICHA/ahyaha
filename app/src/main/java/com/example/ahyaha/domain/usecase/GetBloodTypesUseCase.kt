package com.example.ahyaha.domain.usecase

import com.example.ahyaha.data.repository.BloodTypeRepository
import javax.inject.Inject

class GetBloodTypesUseCase @Inject constructor(private val bloodTypeRepository: BloodTypeRepository) {
    operator fun invoke() = bloodTypeRepository.getAllBloodTypes()
}