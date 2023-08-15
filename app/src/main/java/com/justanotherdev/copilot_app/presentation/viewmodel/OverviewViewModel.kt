package com.justanotherdev.copilot_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.base.SingleLiveEvent
import com.justanotherdev.copilot_app.data.mapper.MotorcycleMapper
import com.justanotherdev.copilot_app.data.repository.MotorcycleRepositoryImpl
import com.justanotherdev.copilot_app.domain.model.Motorcycle
import com.justanotherdev.copilot_app.domain.usecase.GetMotorcycleOverviewUseCase
import kotlinx.coroutines.launch

internal class OverviewViewModel : ViewModel() {

    private var getMotorcycleOverview: GetMotorcycleOverviewUseCase =
        GetMotorcycleOverviewUseCase(MotorcycleRepositoryImpl(MotorcycleMapper::map))

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> get() = _viewState

    private val _actionState = SingleLiveEvent<ActionState>()
    val actionState: SingleLiveEvent<ActionState> get() = _actionState

    init {

        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            // GET CURRENT USER ID
            // GET ACTIVE MOTORCYCLE FOR GIVEN USER ID
            getMotorcycleOverview("6M69bOzIbsVz7QwJ402ePlcT2ts1").let {
                when (it) {
                    is Result.Success -> {
                        // render updates on UI
                        _viewState.value = ViewState.OverviewLoaded(
                            Motorcycle(
                                id = "1",
                                codename = it.data.codename,
                                brand = it.data.brand,
                                status = it.data.status,
                                imagePath = it.data.imagePath,
                            )
                        )
                    }

                    is Result.Failure -> {
                        _viewState.value = ViewState.OverviewLoadFailure(it.error.message!!)
                    }
                }
            }

        }
    }

    fun navigatesToUserProfile() {
        viewModelScope.launch {
            _actionState.value = ActionState.NavigatesToUserProfile
        }
    }

    sealed class ViewState() {
        object Loading : ViewState()
        data class OverviewLoaded(val motorcycle: Motorcycle) : ViewState()
        data class OverviewLoadFailure(val reason: String) : ViewState()
    }

    sealed class ActionState() {
//        object NeedsToPowerEngine : ActionState()
//        object NeedsToSoundAlarm : ActionState()
//        object NeedsToLockOrUnlock : ActionState()

        object NavigatesToUserProfile : ActionState()
        object NavigatesToVehicleDetail : ActionState()


    }
}

class OverviewViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java).newInstance()
    }
}