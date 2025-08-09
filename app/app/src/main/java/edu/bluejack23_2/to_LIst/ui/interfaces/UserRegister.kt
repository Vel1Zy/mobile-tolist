package edu.bluejack23_2.to_LIst.ui.interfaces

class UserRegister(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
) {
    fun updateUsername(username: String): UserRegister {
        return UserRegister(username, this.email, this.password, this.confirmPassword)
    }

    fun updateEmail(email: String): UserRegister {
        return UserRegister(this.username, email, this.password, this.confirmPassword)
    }

    fun updatePassword(password: String): UserRegister {
        return UserRegister(this.username, this.email, password, this.confirmPassword)
    }

    fun updateConfirmPassword(input: String): UserRegister {
        return UserRegister(this.username, this.email, this.password, input)
    }
}