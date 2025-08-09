package edu.bluejack23_2.to_LIst.viewmodel.LoginViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import edu.bluejack23_2.to_LIst.ui.interfaces.UserLogin
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel

class LoginViewModel : ViewModel() {
    var userLogin by mutableStateOf(UserLogin())


    fun setUsername(usernameInput: String) {
        userLogin = userLogin.withUsername(usernameInput)
    }

    fun setPassword(passwordInput: String) {
        userLogin = userLogin.withPassword(passwordInput)
    }

    fun validateUser(): Response<Boolean> {
        var message = ""

        if (userLogin.email.trim().isEmpty()) message = "Email cannot be empty"
        else if (userLogin.password.trim().isEmpty()) message = "Password Cannot be empty"

        val success = message == ""

        return Response(success, message, success)
    }

    suspend fun loginButtonClicked(): Response<Boolean> {
        var result = this.validateUser()

        if (result.data == false) return result

        result = UserRepositories.loginUser(userLogin)

        if (result.data == true) {
            GlobalViewModel.email = this.userLogin.email

            GlobalViewModel.checkAnyUser()
        }
        return result
    }
}