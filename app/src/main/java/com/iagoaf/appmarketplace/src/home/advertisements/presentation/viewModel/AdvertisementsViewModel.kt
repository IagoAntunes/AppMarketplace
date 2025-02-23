package com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.src.home.advertisements.domain.repository.IAdvertisementRepository
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.actions.ListAdvertisementsActions
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.state.ListAdvertisementsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AdvertisementsViewModel(
    val advertisementRepository: IAdvertisementRepository
) : ViewModel() {

    lateinit var navController: NavController


    private val _state = MutableStateFlow<ListAdvertisementsState>(ListAdvertisementsState.Idle)
    val state = _state

    fun onAction(action: ListAdvertisementsActions) {
        when (action) {
            is ListAdvertisementsActions.GetAll -> {
                _state.value = ListAdvertisementsState.Loading
                getAnnouncements()
            }

            is ListAdvertisementsActions.NavigateToAdvertisementDetail -> {
                navController.navigate(action.advertisement)
            }

            is ListAdvertisementsActions.FilterList -> {
                val filterValue = action.filterValue
                (_state.value as? ListAdvertisementsState.Success)?.let { successState ->
                    _state.value = successState.copy(
                        filterAdvertisements = successState.advertisements.filter { advertisement ->
                            advertisement.title.contains(filterValue, ignoreCase = true)
                        }
                    )
                }
                Log.d("FilterList", "Filtered list: ${_state.value}")
                Log.d(
                    "List Adv",
                    "list: ${(_state.value as ListAdvertisementsState.Success).filterAdvertisements.size.toString()}"
                )
            }
        }
    }

    private fun getAnnouncements() {
        viewModelScope.launch {
            _state.value = ListAdvertisementsState.Loading
            advertisementRepository.getAnnouncements().onSuccess { advertisements ->
                _state.value = ListAdvertisementsState.Success(
                    advertisements = advertisements,
                    filterAdvertisements = advertisements,
                )
            }.onError {
                _state.value = ListAdvertisementsState.Error(it.message ?: "Unknown error")
            }
        }
    }

}