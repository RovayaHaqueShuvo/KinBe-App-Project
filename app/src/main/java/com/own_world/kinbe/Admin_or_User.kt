package com.own_world.kinbe

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.own_world.kinbe.databinding.ActivityAdminOrUserBinding

class Admin_or_User : AppCompatActivity() {
    private lateinit var binding: ActivityAdminOrUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAdminOrUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.sellerBtn.setOnClickListener {
            val intent = Intent(this@Admin_or_User, SignUp_Activity::class.java)

           val bundle = Bundle()
            bundle.putInt("Value", 1)

            intent.putExtras(bundle)
            startActivity(intent)

        }

        binding.buyerBtn.setOnClickListener {
            val intent = Intent(this@Admin_or_User, SignUp_Activity::class.java)

            val bundle = Bundle()
            bundle.putInt("Value", 2)

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}