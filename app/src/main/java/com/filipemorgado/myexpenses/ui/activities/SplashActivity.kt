package com.filipemorgado.myexpenses.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.filipemorgado.myexpenses.databinding.ActivitySplashMainBinding
import com.filipemorgado.myexpenses.utilities.SPLASH_SCREEN_DURATION

/**
 * Activity used only just for the splash screen
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add a delay of 2000 milliseconds (2 seconds) before starting MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the splash screen activity so it's not accessible via the back button
        }, SPLASH_SCREEN_DURATION)
    }
}
