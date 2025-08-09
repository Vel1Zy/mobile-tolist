package edu.bluejack23_2.to_LIst.viewmodel.RegisterViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.builder.UserBuilder
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import edu.bluejack23_2.to_LIst.ui.interfaces.UserRegister
import edu.bluejack23_2.to_LIst.utils.Response

class RegisterViewModel : ViewModel() {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    var userRegister by mutableStateOf(UserRegister())
    val minimalLength = 10
    private fun validateUserRegister(): Response<Boolean> {
        var message: String = ""


        if (userRegister.email.isEmpty()) message = "Please fill in Email!"
        else if (userRegister.username.isEmpty()) message = "Please fill in Username"
        else if (userRegister.password.isEmpty()) message = "Please fill in Password"
        else if (!userRegister.email.matches(this.emailRegex)) message = "Email format is not right"
        else if (userRegister.password != userRegister.confirmPassword) message =
            "Confirm Password is not the same"
        else if (userRegister.password.length < this.minimalLength) message =
            "Password is not long enough"


        return Response(message == "", message, message == "")
    }

    suspend fun registerButtonClicked(): Response<Boolean> {
        val validationResult = validateUserRegister()

        if (validationResult.data == false) return validationResult

        val user: User = UserBuilder.createUser(userRegister)

        val createResponse = UserRepositories.createUser(user)

        return createResponse
    }

    fun updateUsername(usernameInput: String) {
        this.userRegister = userRegister.updateUsername(usernameInput)
    }

    fun updatePassword(passwordInput: String) {
        this.userRegister = userRegister.updatePassword(passwordInput)
    }

    fun updateEmail(emailInput: String) {
        this.userRegister = userRegister.updateEmail(emailInput)
    }

    fun updateConfirmPassword(input: String) {
        this.userRegister = userRegister.updateConfirmPassword(input)
    }
}