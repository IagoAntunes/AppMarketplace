package com.iagoaf.appmarketplace.src.home.profile.presentation.state

import com.iagoaf.appmarketplace.src.auth.register.domain.models.UserModel

sealed class ProfileNavItemState {
    object Idle : ProfileNavItemState()
    object Loading : ProfileNavItemState()
    object Error : ProfileNavItemState()
    object SuccessLogout : ProfileNavItemState()
    data class Success(val user: UserModel) : ProfileNavItemState()
}