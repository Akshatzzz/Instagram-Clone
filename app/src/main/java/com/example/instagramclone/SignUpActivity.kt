package com.example.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagramclone.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerClickListener()
    }

    private fun registerClickListener() {
        if (::binding.isInitialized) {
            binding.apply {
                registerButton.setOnClickListener {
                    val name = this.etName.text.toString()
                    if (name.isEmpty()) {
                        Snackbar.make(
                            this.root, "Empty name field", Snackbar.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                    val email = etEmail.text.toString()
                    if (email.isEmpty()) {
                        Snackbar.make(
                            this.root, "empty email field", Snackbar.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                    val password = etPassword.text.toString()
                    if (password.isEmpty()) {
                        Snackbar.make(
                            this.root, "Empty password field", Snackbar.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful) {
                                Snackbar.make(
                                    this.root, "Successful Sign up !!", Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                Snackbar.make(
                                    this.root, "Something went wrong 😔", Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    clearTextFields()
                }
            }
        }
    }

    private fun clearTextFields() {
        binding.apply {
            etEmail.text?.clear()
            etName.text?.clear()
            etPassword.text?.clear()
        }
    }
}