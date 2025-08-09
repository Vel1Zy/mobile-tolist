package edu.bluejack23_2.to_LIst.ui.interfaces

class UserLogin(var email: String = "", var password: String = "") {
    fun withUsername(username: String): UserLogin {
        return UserLogin(username, this.password)
    }

    fun withPassword(password: String): UserLogin {
        return UserLogin(this.email, password)
    }
}