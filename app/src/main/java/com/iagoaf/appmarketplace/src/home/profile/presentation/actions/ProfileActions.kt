package com.iagoaf.appmarketplace.src.home.profile.presentation.actions

sealed class ProfileActions {
    object LoadUserInfo : ProfileActions()
    object Logout : ProfileActions()
}