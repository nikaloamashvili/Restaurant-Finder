package com.example.nika.android2.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.nika.android2.databinding.ActivitySignUpBinding
import com.example.nika.android2.models.User
import com.example.nika.android2.server.RetrieveFeedTask
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var actionBar: ActionBar

    private lateinit var  progressDialog: ProgressDialog

    private lateinit var  firebaseAuth : FirebaseAuth
    private var email =""
    private var password=""
    private var name =""
    private var phone=""
    private var age =""
    private var address=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar =supportActionBar!!
        actionBar.title="sign up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener{
            vaildateData()
        }
    }
    private fun vaildateData(){
        email =binding.EmailEt.text.toString().trim()
        password =binding.passwordEt.text.toString().trim()
        name =binding.nameEt.text.toString().trim()
        phone=binding.phoneEt.text.toString().trim()
        age=binding.ageEt.text.toString().trim()
        address=binding.addressET.text.toString().trim()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.EmailEt.error ="invaild email format"
        }else if(TextUtils.isEmpty(password)){
            binding.passwordEt.error ="enter password"
        }else if(password.length <6){
            binding.passwordEt.error ="password length most be 6 characters"
        }else{
            firebaseSignUP()
        }
    }

    private fun firebaseSignUP(){
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Toast.makeText( this, name,Toast.LENGTH_SHORT).show()
                Toast.makeText( this, phone,Toast.LENGTH_SHORT).show()

                val r = RetrieveFeedTask("0", User(name,phone,age,address, email,"0"))
                r.execute()
                progressDialog.dismiss()
                val firebaseuser = firebaseAuth.currentUser
                val email =firebaseuser!!.email
                Toast.makeText( this, "you sign up",Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText( this, "faild due $e",Toast.LENGTH_SHORT).show()

            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }



}