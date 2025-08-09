package edu.bluejack23_2.to_LIst.model.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.storage
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.utils.Response
import kotlinx.coroutines.tasks.await
import java.util.UUID

object ProfileRepository {
    private var database: DatabaseReference = FirebaseEnum.userDatabaseReference
    private var storage = Firebase.storage
    private var storageRef = storage.reference


    @SuppressLint("RestrictedApi")
    suspend fun uploadProfileImage(
        imageUri: Uri,
        context: Context,
        type: String = "profile-image"
    ): String? {
        try {
            val uniqueImageName = UUID.randomUUID()

            var spaceRef: StorageReference = storageRef.child("profile-image/$uniqueImageName.jpg")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(imageUri)
                ?.use { it.readBytes() }

            if (byteArray != null) {
                val something = spaceRef.putBytes(byteArray).await()
                return spaceRef.downloadUrl.await().toString()
            }

            return null

        } catch (e: Exception) {
            Log.d("Uploading Image Failed", e.toString())
            return null
        }
    }

    suspend fun updateProfile(user: User): Response<Boolean> {
        try {
            Log.d("Updating user", "new username  userID : ${user}")

//            database.child(user.id).setValue(user).await()
            FirebaseEnum.userDatabaseReference.child(user.id).child("username")
                .setValue(user.username).await()
            FirebaseEnum.userDatabaseReference.child(user.id).child("dateOfBirth")
                .setValue(user.dateOfBirth).await()
            FirebaseEnum.userDatabaseReference.child(user.id).child("profileImageLink")
                .setValue(user.profileImageLink).await()

            return Response(true, "Success Updating User", true)
        } catch (e: Exception) {
            Log.e("Updating user", e.printStackTrace().toString())
            return Response(false, "Failed updating user", false)
        }
    }


}