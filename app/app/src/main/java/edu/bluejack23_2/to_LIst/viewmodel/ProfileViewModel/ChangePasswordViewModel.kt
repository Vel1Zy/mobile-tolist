package edu.bluejack23_2.to_LIst.viewmodel.ProfileViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class ChangePasswordViewModel : ViewModel() {
    var password by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var verificationInput by mutableStateOf("")
    val verificationCodes: MutableList<String> = mutableListOf("")

    private fun validateCreation(user: User?): Response<Boolean> {
        var message = ""
        if (password.trim().isEmpty()) message = "Password cannot be empty"
        else if (newPassword.trim().isEmpty()) message = "New Password cannot be empty"
        else if (user == null) message = "There is no user logged in"
        else if (password != user.password) message = "Password is wrong"
        val success = message == ""

        return Response(success, message, success)
    }

    fun generateRandomString(length: Int): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') // Define character pool
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    val emailCooldowns = ConcurrentHashMap<String, Long>()
    val cooldownDurationMillis = 30000L

    suspend fun sendVerification(): Boolean {
        val lastSentTime = emailCooldowns[GlobalViewModel.email] ?: 0
        val currentTime = System.currentTimeMillis()

        return if (currentTime - lastSentTime < cooldownDurationMillis) {
            Log.d("Cooldown", "Email to ${GlobalViewModel.email} is on cooldown. Please try again later.")
            false
        } else {
            try {
                val verificationCode = generateRandomString(6)
                withContext(Dispatchers.IO) {
                    UserRepositories.sendEmail(
                        "williamqwerty3@gmail.com",
                        "kwra fvom bvqr fqhe",
                        GlobalViewModel.email,
                        "Change Password OTP",
                        verificationCode
                    )
                }

                verificationCodes.add(verificationCode)
                emailCooldowns[GlobalViewModel.email] = currentTime
                true
            } catch (e: Exception) {
                Log.d("Sending Verification", e.toString())
                false
            }
        }
    }
    fun checkVerification(): Boolean {
        for (code in this.verificationCodes) {
            if (code == this.verificationInput) return true
        }
        return false
    }

    suspend fun clickHandle(): Response<Boolean> {
        val user = GlobalViewModel.getUserInformation()

        val validationResult = this.validateCreation(user)

        if (validationResult.data == false) return validationResult

        if (user != null) {
            val result = UserRepositories.changePassword(user, newPassword)
            return Response(result, "$result changing password", result)
        }

        return validationResult
    }
}