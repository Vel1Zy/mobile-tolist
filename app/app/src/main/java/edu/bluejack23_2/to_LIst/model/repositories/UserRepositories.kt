package edu.bluejack23_2.to_LIst.model.repositories

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import edu.bluejack23_2.to_LIst.enumerations.FirebaseEnum
import edu.bluejack23_2.to_LIst.model.models.User
import edu.bluejack23_2.to_LIst.ui.interfaces.UserLogin
import edu.bluejack23_2.to_LIst.utils.Response
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object UserRepositories {

    private var auth: FirebaseAuth = Firebase.auth
    private var database: DatabaseReference = FirebaseEnum.userDatabaseReference

    suspend fun createUser(user: User): Response<Boolean> {
        try {
            Log.d("Creating user", "${user.email}, ${user.password}")
            database.child(user.id).setValue(user).await()
            val registerResult = registerUser(user)
            return registerResult
        } catch (e: Exception) {
            Log.d("Creating user", e.toString())
            return Response(false, "failed creating user", false)
        }
    }

    private suspend fun registerUser(user: User): Response<Boolean> {
        try {
            this.auth.createUserWithEmailAndPassword(user.email, user.password).await()
            return Response(true, "Successed Creating User", true)
        } catch (e: Exception) {
            Log.d("Register user", e.toString())
            return Response(false, "Failed Creating User", false)
        }
    }

    suspend fun getUserInformationByEmail(emailInput: String?): User? {

        try {
            var findingUser: User? = null
            val dataSnapshot = FirebaseEnum.userDatabaseReference.get().await()

            for (userSnapshot in dataSnapshot.children) {
                val user = userSnapshot.getValue(User::class.java) ?: continue

                Log.d("user repo", user.email)

                if (user.email == emailInput) {
                    Log.d("user repo", user.username)
                    Log.d("user repo", "user found")
                    findingUser = user
                    break
                }
            }

            return findingUser
        } catch (e: Exception) {
            Log.d("get user information by email", e.toString())

            return null
        }
    }

    suspend fun getUserByID(userId: String): User? {
        try {
            val dataSnapshot = this.database.child(userId).get().await()
            val user = dataSnapshot.getValue(User::class.java)

            return user
        } catch (e: Exception) {
            Log.d("Getingg user by id", e.toString())
            return null
        }
    }

    fun fetchUser(users: MutableStateFlow<List<User>>) {
        this.database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val usersList = mutableListOf<User>()

                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java) ?: continue
                    usersList.add(user)
                }

                users.value = usersList
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }

    suspend fun changePassword(user: User, newPassword: String): Boolean {
        try {
            this.auth.currentUser?.updatePassword(newPassword)?.await()
            this.database.child(user.id).child("password").setValue(newPassword).await()
            return true
        } catch (e: Exception) {
            Log.d("changing password", e.toString())
            return false
        }
    }

    fun logOutUser(): Boolean {
        try {
            this.auth.signOut()
            return true
        } catch (e: Exception) {
            Log.d("logging out user", e.toString())
            return false
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    suspend fun loginUser(user: UserLogin): Response<Boolean> {
        try {
            this.auth.signInWithEmailAndPassword(user.email, user.password).await()
            return Response(true, "Success Login User", true)
        } catch (e: Exception) {
            Log.d("login user", e.toString())
            return Response(false, "Failed Login User", false)
        }
    }

    suspend fun sendEmail(
        userName: String,
        password: String,
        toEmail: String,
        subject: String,
        body: String
    ) = withContext(Dispatchers.IO) {
        val props = Properties().apply {
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", "smtp.gmail.com") // for Gmail
            put("mail.smtp.port", "587")
        }

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(userName, password)
            }
        })

        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(userName))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                setSubject(subject)
                setText(body)
            }

            Transport.send(message)
            println("Email Sent Successfully")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteUser(): Response<Boolean> {
        try {
            auth.currentUser?.delete()
            val currentUser = GlobalViewModel.getUserInformation()

            if (currentUser != null) {
                this.database.child(currentUser.id).removeValue().await()
                GlobalViewModel.logOut()
                return Response(true, "Sucess deleting User", true)
            }
            return Response(false, "failed deleting User", false)
        } catch (e: Exception) {
            return Response(false, "failed deleting User", false)
        }
    }
}