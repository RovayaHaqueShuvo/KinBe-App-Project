package com.own_world.kinbe

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.own_world.kinbe.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
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

        val bun = getIntent().getExtras()
        val Value = bun!!.getInt("Value")
        if(Value == 1){
            binding.retailerLogin.setVisibility(View.GONE)
            binding.customerLogin.setVisibility(View.VISIBLE)


        }
        else{
            binding.retailerLogin.setVisibility(View.VISIBLE)
            binding.customerEmail.setVisibility(View.GONE)
        }

        binding.retailerTextView6.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUp_Activity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 1)

            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.customerTextView6.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUp_Activity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 2)

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }


}