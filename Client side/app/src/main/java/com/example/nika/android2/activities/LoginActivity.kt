package com.example.nika.android2.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.nika.android2.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {



private lateinit var binding: ActivityLoginBinding

private lateinit var actionBar: ActionBar

private lateinit var progressDialog:ProgressDialog

private lateinit var  firebaseAuth :FirebaseAuth
private var email =""
private var password=""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar= supportActionBar!!
        actionBar.title ="Login"
        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In")
        progressDialog.setCanceledOnTouchOutside(false)

        checkUser()

        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.LoginBtn.setOnClickListener{

//            Repository.getArrState()
            validateData()
        }
    }

    private fun validateData(){

        email =binding.EmailEt.text.toString().trim()
        password =binding.passwordEt.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.EmailEt.setError("Invaild email format")
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEt.error="Please enter password"
        }
        else{

            firebaseLogin()

        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()

                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"hello $email",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, myLocation::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this,"Login faild dur to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            startActivity(Intent(this, myLocation::class.java))
            finish()
        }
    }


}

