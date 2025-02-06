package com.iagoaf.appmarketplace.src.home.advertisements.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
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
        }
    }

    private fun getAnnouncements() {
        viewModelScope.launch {
            _state.value = ListAdvertisementsState.Loading
            advertisementRepository.getAnnouncements().onSuccess { advertisements ->
                _state.value = ListAdvertisementsState.Success(advertisements)
            }.onError {
                _state.value = ListAdvertisementsState.Error(it.message ?: "Unknown error")
            }
        }
    }

}