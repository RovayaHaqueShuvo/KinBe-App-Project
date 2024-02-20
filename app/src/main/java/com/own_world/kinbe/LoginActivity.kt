package com.own_world.kinbe

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.own_world.kinbe.Modal.User
import com.own_world.kinbe.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recive data from sign up activity
        val bun = getIntent().getExtras()
        val Value = bun!!.getInt("Value")
        if (Value == 1) {
            binding.retailerLogin.setVisibility(View.GONE)
            binding.customerLogin.setVisibility(View.VISIBLE)


        } else {
            binding.retailerLogin.setVisibility(View.VISIBLE)
            binding.customerEmail.setVisibility(View.GONE)
        }


        //bundle passing to retailer sign up activity
        binding.retailerTextView6.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUp_Activity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 1)

            intent.putExtras(bundle)
            startActivity(intent)
        }

        //bundle passing to customer sign up activity
        binding.customerTextView6.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUp_Activity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 2)

            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.customerLoginBtn.setOnClickListener {
            if (binding.customerEmail.editText?.text.toString()
                    .isEmpty() || binding.customerPassword.editText?.text.toString().isEmpty()
            ) Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            else {
                Firebase.auth.signInWithEmailAndPassword(
                    binding.customerEmail.editText?.text.toString(),
                    binding.customerPassword.editText?.text.toString()
                )
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
        binding.retailerLoginBtn.setOnClickListener {
            if (binding.retailerEmail.editText?.text.toString()
                    .isEmpty() || binding.retailerPassword.editText?.text.toString().isEmpty()
            ) Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            else {
                Firebase.auth.signInWithEmailAndPassword(
                    binding.retailerEmail.editText?.text.toString(),
                    binding.retailerPassword.editText?.text.toString()
                )
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }

    }
}