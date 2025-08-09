package edu.bluejack23_2.to_LIst.enumerations

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class FirebaseEnum {
    companion object {
        var USER: String = "users"
        var TODOLIST: String = "todolists"
        var databaseReference: DatabaseReference =
            Firebase.database("https://to-list-74f5b-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        val userDatabaseReference = this.databaseReference.child(this.USER)
        val toDoListDatabaseReference = this.databaseReference.child(this.TODOLIST)

    }
}