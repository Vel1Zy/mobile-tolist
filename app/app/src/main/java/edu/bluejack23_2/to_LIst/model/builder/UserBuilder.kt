package edu.bluejack23_2.to_LIst.model.builder

import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.ui.interfaces.UserRegister
import java.util.UUID

class UserBuilder {
    companion object {
        fun createUser(userRegister: UserRegister): User {
            return User(
                UUID.randomUUID().toString(),
                userRegister.username,
                userRegister.email,
                userRegister.password,
                mutableListOf()
            )
        }
    }
}