package com.example.nika.android2.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.nika.android2.activities.showActivity
import com.example.nika.android2.activities.showActivity2
import com.example.nika.android2.databinding.ActivityProfile2Binding
import com.example.nika.android2.models.User
import com.example.nika.android2.server.RetrieveFeedTask
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProfile2Binding

    private lateinit var actionBar: ActionBar

    private  lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var user : User ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfile2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        user=RetrieveFeedTask.returnuser()
        binding.age.setText("age: "+user.age);
        binding.phone.setText("phone: "+user.phone);
        binding.name.setText("name: "+user.name);
        binding.address.setText("address: "+user.address);
        binding.email.setText("mail: "+user.mail);
        binding.admin.setText("admin: "+user.admin)
        binding.button.setOnClickListener { e->startActivity(Intent(this, showActivity2::class.java)) };


        //actionBar = supportActionBar!!
        //actionBar.title="profile"


    }

    private fun checkUser(): String? {
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser != null) {
            return firebaseUser.email
        };
        return null
    }



}