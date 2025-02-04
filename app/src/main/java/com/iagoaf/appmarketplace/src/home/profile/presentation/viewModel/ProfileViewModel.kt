package com.iagoaf.appmarketplace.src.home.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.core.result.onError
import com.iagoaf.appmarketplace.core.result.onSuccess
import com.iagoaf.appmarketplace.core.routes.AppMarketplaceRoutes
import com.iagoaf.appmarketplace.src.auth.register.domain.repository.IAuthRepository
import com.iagoaf.appmarketplace.src.home.profile.presentation.actions.ProfileActions
import com.iagoaf.appmarketplace.src.home.profile.presentation.state.ProfileNavItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    val authRepository: IAuthRepository
) : ViewModel() {

    lateinit var appNavController: NavController

    private val _state = MutableStateFlow<ProfileNavItemState>(ProfileNavItemState.Idle)
    val state = _state

    fun onAction(action: ProfileActions) {
        when (action) {
            ProfileActions.LoadUserInfo -> {
                loadUserInfo()
            }

            ProfileActions.Logout -> {
                logoutUser()
            }
        }
    }

    private fun loadUserInfo() {
        _state.value = ProfileNavItemState.Loading
        viewModelScope.launch {
            authRepository.currentUser()
                .onSuccess { user ->
                    _state.value = ProfileNavItemState.Success(user)
                }.onError {

                }
        }
    }

    private fun logoutUser() {
        viewModelScope.launch {
            authRepository.logout()
                .onSuccess {
                    appNavController.navigate(AppMarketplaceRoutes.LoginScreenRoute.name) {
                        popUpTo(appNavController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }.onError {

                }
        }
    }

}