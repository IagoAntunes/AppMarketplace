package com.iagoaf.appmarketplace.src.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.iagoaf.appmarketplace.src.auth.register.RegisterActions

class RegisterViewModel : ViewModel() {

    lateinit var navController: NavController


    fun onAction(action: RegisterActions) {
        when (action) {
            is RegisterActions.GoBackToLogin -> {
                goBackToLogin()
            }

            RegisterActions.Register -> {}
        }
    }

    private fun goBackToLogin() {
        navController.popBackStack()
    }


}