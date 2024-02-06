package com.own_world.kinbe

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        SplashScreen()
    }
    private fun SplashScreen() {
        launchOpratation().execute()
    }

    private open inner class launchOpratation : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {

            for (i in 0..3) {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    Thread.interrupted()
                }
            }
            return String()
        }

        override fun onPostExecute(result: String?) {
            val intent = Intent(this@MainActivity, Admin_or_User::class.java)
            startActivity(intent)
            finish()
        }
    }
}