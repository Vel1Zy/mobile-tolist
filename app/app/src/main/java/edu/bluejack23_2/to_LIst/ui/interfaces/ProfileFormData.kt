package edu.bluejack23_2.to_LIst.ui.interfaces

import java.time.LocalDate

class ProfileFormData(
    var profilePictureUrl: String = "",
    var username: String = "",
    var email: String = "",
    var dateOfBirth: String = LocalDate.now().toString(),

    ) {

    fun withProfilePictureUrl(profilePictureUrlInput: String): ProfileFormData {
        return ProfileFormData(
            profilePictureUrlInput,
            this.username,
            this.email,
            this.dateOfBirth
        )
    }

    fun withUsername(usernameInput: String): ProfileFormData {
        return ProfileFormData(
            this.profilePictureUrl,
            usernameInput,
            this.email,
            this.dateOfBirth
        )
    }

    fun withEmail(emailInput: String): ProfileFormData {
        return ProfileFormData(
            this.profilePictureUrl,
            this.username,
            emailInput,
            this.dateOfBirth
        )
    }

    fun withDateOfBirth(dateOfBirthInput: String): ProfileFormData {
        return ProfileFormData(
            this.profilePictureUrl,
            this.username,
            this.email,
            dateOfBirthInput
        )
    }
}