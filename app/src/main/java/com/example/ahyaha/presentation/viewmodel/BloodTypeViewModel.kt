package com.example.ahyaha.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahyaha.data.model.BloodType
import com.example.ahyaha.data.repository.BloodTypeRepositoryImpl
import com.example.ahyaha.domain.usecase.GetBloodTypesUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BloodTypeState(
    val bloodTypes: List<BloodType> = emptyList()
)


@HiltViewModel
class BloodTypeViewModel @Inject constructor(private val getBloodTypesUseCase: GetBloodTypesUseCase) : ViewModel() {

    private val _bloodTypeState = MutableStateFlow(BloodTypeState())
    val bloodTypeState: StateFlow<BloodTypeState> = _bloodTypeState.asStateFlow()


    init {
        getBloodTypes()
    }

    private fun getBloodTypes() {
        viewModelScope.launch {
            getBloodTypesUseCase().collect{bloodTypes ->
                _bloodTypeState.value = _bloodTypeState.value.copy(bloodTypes = bloodTypes)
            }
        }
    }
}
