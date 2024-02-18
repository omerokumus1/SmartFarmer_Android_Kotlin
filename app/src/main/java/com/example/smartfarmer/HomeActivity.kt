package com.example.smartfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.smartfarmer.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        binding.savedDataBtn.setOnClickListener {
            Intent(this, SavedDataActivity::class.java).also { startActivity(it) }
        }
        binding.readDataBtn.setOnClickListener {
            Intent(this, MainActivity::class.java).also { startActivity(it) }
        }
    }
    private fun setStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = resources.getColor(R.color.colorPrimary)
    }
}