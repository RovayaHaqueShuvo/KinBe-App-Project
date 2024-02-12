package com.own_world.kinbe

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.own_world.kinbe.Modal.User
import com.own_world.kinbe.Utils.USER_COSTOMER
import com.own_world.kinbe.Utils.USER_RETALIER
import com.own_world.kinbe.databinding.ActivitySignUpBinding

class SignUp_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var user: User
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        user = User()

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
        if (Value == 1) {
            binding.customerSignup.setVisibility(View.GONE)
            binding.retailerSignup.setVisibility(View.VISIBLE)


        } else {
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
        binding.customerSignupBtn.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.customerEmail.editText?.text.toString(),
                binding.customerPassword.editText?.text.toString()
            ).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    user.username = binding.customerName.editText?.text.toString()
                    user.useremail = binding.customerEmail.editText?.text.toString()
                    user.password = binding.customerPassword.editText?.text.toString()

                   db!!.collection(USER_COSTOMER)
                        .document(Firebase.auth.currentUser?.email.toString()).set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this@SignUp_Activity, "Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUp_Activity, HomeActivity::class.java))
                        }
                       .addOnFailureListener {
                           Toast.makeText(this@SignUp_Activity, "Failed", Toast.LENGTH_SHORT).show()
                       }
                }

            }
        }


        binding.retailerSignupBtn.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.retailerEmail.editText?.text.toString(),
                binding.retailerPassword.editText?.text.toString()
            ).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    user.username = binding.retailerName.editText?.text.toString()
                    user.useremail = binding.retailerEmail.editText?.text.toString()
                    user.password = binding.retailerPassword.editText?.text.toString()

                   db!!.collection(USER_RETALIER)
                        .document(Firebase.auth.currentUser?.email.toString()).set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this@SignUp_Activity, "Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUp_Activity, HomeActivity::class.java))
                        }
                       .addOnFailureListener {
                           Toast.makeText(this@SignUp_Activity, "Failed", Toast.LENGTH_SHORT).show()
                       }
                }

            }
        }
    }
}