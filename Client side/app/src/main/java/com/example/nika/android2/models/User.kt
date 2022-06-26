package com.example.nika.android2.models

class User {
     lateinit var name: String
     lateinit var phone: String
     lateinit var age: String
     lateinit var address: String
     lateinit var mail: String
    lateinit var admin: String

    constructor(name: String, phone: String,age: String, address: String, mail: String, admin: String) {
        this.name = name
        this.phone = phone
        this.age = age
        this.address = address
        this.mail = mail
        this.admin=admin
    }



}