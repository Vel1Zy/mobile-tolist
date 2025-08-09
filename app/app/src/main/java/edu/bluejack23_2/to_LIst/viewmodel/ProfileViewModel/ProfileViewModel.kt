package edu.bluejack23_2.to_LIst.viewmodel.ProfileViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.repositories.ProfileRepository
import edu.bluejack23_2.to_LIst.ui.interfaces.ProfileFormData
import edu.bluejack23_2.to_LIst.utils.DateHelper
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import java.time.LocalDate

class ProfileViewModel : ViewModel() {


    var profileFormData by mutableStateOf(ProfileFormData())

    init {
        val user = GlobalViewModel.getUserInformation()

        if (user != null) {
            profileFormData.email = user.email
            profileFormData.username = user.username
            profileFormData.profilePictureUrl = user.profileImageLink
            profileFormData.dateOfBirth = user.dateOfBirth
        }
    }
    // store data yang mau diganti

    @SuppressLint("RestrictedApi")
    suspend fun uploadUserProfileImage(imageUri: Uri, context: Context): Response<String> {

        var profileImageLink: String? = ProfileRepository.uploadProfileImage(imageUri, context)

        if (profileImageLink != null) {
            Toast.makeText(
                context,
                "upload success, get download url success",
                Toast.LENGTH_SHORT
            ).show()
            return Response(profileImageLink, "Get Download URL Success", true)
        }

        Toast.makeText(
            context,
            "upload failed, get download url failed, Please try again",
            Toast.LENGTH_SHORT
        ).show()
        return Response("", "Get Download URL Failed", false)
    }

    fun validateUserProfileUpdate(profileData: ProfileFormData): Response<Boolean> {
        var message = ""
        if (profileData.username.isEmpty()) message = "username cannot be empty"
        else if (profileData.profilePictureUrl.isEmpty()) message =
            "Please upload new profile picture"

        val dob = DateHelper.convertStringToDate(profileData.dateOfBirth)
        val dateChecker = LocalDate.now().minusYears(13)
        if (dob.isAfter(dateChecker)) message = "Age is before 13 years old"

        return Response(message == "", message, message == "")
    }

    suspend fun updateUserProfile(newProfileData: ProfileFormData): Response<Boolean> {
        val user = GlobalViewModel.getUserInformation()
        if (user == null) {
            Log.e("FHASKHFJKSAJKHFSAHDHJKDHJKASHJKFHASJK gagal", "ASHDJKAHJDKAHSJKDHSAJKD")
            return Response(false, "Update Profile Failed123124", false)
        }

        val validationResult = this.validateUserProfileUpdate(newProfileData)

        if (validationResult.data == false) return validationResult

        user.username = newProfileData.username
        user.dateOfBirth = newProfileData.dateOfBirth.toString()

        user.dateOfBirth = newProfileData.dateOfBirth
        user.profileImageLink = newProfileData.profilePictureUrl


        val response = ProfileRepository.updateProfile(user)

        return response
    }
}