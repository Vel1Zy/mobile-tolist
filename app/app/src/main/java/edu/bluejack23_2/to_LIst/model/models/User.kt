package edu.bluejack23_2.to_LIst.model.models

import java.time.LocalDate
import java.util.Date

class User() {
    lateinit var id: String
    lateinit var username: String
    lateinit var email: String
    lateinit var password: String
    lateinit var toDoList: MutableList<String>
    var profileImageLink: String = ""
    var dateOfBirth: String = ""

    //Constructor for creating the first time (no DOB, no ProfileImageLink)
    constructor(
        id: String,
        username: String,
        email: String,
        password: String,
        toDoList: MutableList<String>,
    ) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.toDoList = toDoList
    }

    constructor(
        id: String,
        username: String,
        email: String,
        password: String,
        toDoList: MutableList<String>,
        profileImageLink: String,
        dateOfBirth: String
    ) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.toDoList = toDoList
        this.profileImageLink = profileImageLink
        this.dateOfBirth = dateOfBirth
    }
}