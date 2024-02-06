package com.own_world.kinbe

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.own_world.kinbe.databinding.ActivitySignUpBinding

class SignUp_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text =
            "<font color= #FF000000> Already have an Account? <font/> <font color= #1E88E5> Login<font/>"
        binding.customerLoginTextView6.setText(Html.fromHtml(text))

        val text2 =
            "<font color= #FF000000> Already have an Account? <font/> <font color= #1E88E5> Login<font/>"
        binding.retailerLoginTextView6.setText(Html.fromHtml(text2))


        val bun = getIntent().getExtras()
        val Value = bun!!.getInt("Value")
        if(Value == 1){
            binding.customerSignup.setVisibility(View.GONE)
            binding.retailerSignup.setVisibility(View.VISIBLE)


        }
        else{
            binding.customerSignup.setVisibility(View.VISIBLE)
            binding.retailerSignup.setVisibility(View.GONE)
        }



        binding.customerLoginTextView6.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 1)

            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.retailerLoginTextView6.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("Value", 2)

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}