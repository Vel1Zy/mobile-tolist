package edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.model.repositories.UserRepositories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.log

object GlobalViewModel {
    private var user: User? = null
    private var firebaseUser: FirebaseUser? = null
    val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = this._isLoggedIn.asStateFlow()
    var email: String = ""
    private fun setNoUser() {
        this.user = null
        Log.d("global view model", "setting no user")
    }

    private suspend fun setNewUser() {
        Log.d("global view model", "setting new user")
        val fireUser = this.firebaseUser ?: return

        val userInformation = UserRepositories.getUserInformationByEmail(this.email)
        Log.d("global view model", userInformation?.email ?: "null email nya brok")

        if (userInformation == null) {
            this.setNoUser()
            return
        }


        Log.d("global view model", "there is no user")
        this.user = userInformation
        this._isLoggedIn.value = false
    }

    suspend fun checkAnyUser() {
        Log.d("global view model", "checking any user")
        this.firebaseUser = UserRepositories.getCurrentUser()
//        if (this.firebaseUser == null) {
//            this.setNoUser()
//            return
//        }

        this.setNewUser()
    }

    fun getUserInformation(): User? {
        return this.user
    }

    fun logOut() {
        this.user = null
        this._isLoggedIn.value = false
        this.email = ""
    }

    fun setUserLoggedIn(loggedIn: Boolean) {
        this._isLoggedIn.value = loggedIn
    }

    private fun setUser(user: User) {
        this.user = user
    }

    private fun getUser(): User? {
        return this.user
    }
}