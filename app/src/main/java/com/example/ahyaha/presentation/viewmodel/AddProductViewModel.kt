package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ahyaha.domain.usecase.AddDonorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddDonorViewModel @Inject constructor(private val addDonorUseCase: AddDonorUseCase) : ViewModel() {

    //TODO: Add Donor logic

}