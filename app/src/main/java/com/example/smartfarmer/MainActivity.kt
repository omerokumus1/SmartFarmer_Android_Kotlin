package com.example.smartfarmer

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartfarmer.databinding.ActivityMainBinding
import com.example.smartfarmer.db.WeatherData
import com.example.smartfarmer.db.WeatherDataRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherDataRepository: WeatherDataRepository

    private var topraginNemi = "0.0"
    private var topraginSicakligi = "0.0"
    private var havaninNemi = "0.0"
    private var havaninSicakligi = "0.0"
    private var ruzgarDurumuYonu = ""
    private var ruzgarHizi = "0.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
        setStatusBar()
        weatherDataRepository = WeatherDataRepository.getInstance(applicationContext)
        setReadDataBtnClickListener()
        setSaveDataBtnCLickListener()
        setDisplaySavedDataBtnClickListener()
    }

    private fun setDisplaySavedDataBtnClickListener() {
        binding.displaySavedDataBtn.setOnClickListener {
            Intent(this, SavedDataActivity::class.java).also { startActivity(it) }
        }
    }

    private fun setStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = resources.getColor(R.color.colorPrimary)
    }

    private fun setActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun setSaveDataBtnCLickListener() {
        binding.saveDataBtn.setOnClickListener {
            if (ruzgarDurumuYonu.isEmpty()) {
                Toast.makeText(this, "Önce Verileri Alınız", Toast.LENGTH_SHORT).show()
            } else {
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
                val current = formatter.format(time)
                val weatherData = WeatherData(
                    date = current,
                    topraginNemi = topraginNemi,
                    topraginSicakligi = topraginSicakligi,
                    havaninNemi = havaninNemi,
                    havaninSicakligi = havaninSicakligi,
                    ruzgarDurumuYonu = ruzgarDurumuYonu,
                    ruzgarHizi = ruzgarHizi
                )
                weatherDataRepository.insertWeatherData(weatherData)
                Toast.makeText(this, "Veriler Kaydedildi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setReadDataBtnClickListener() {
        binding.readDataBtn.setOnClickListener {
            val progress =
                ProgressDialog.show(this, "Veriler Alınıyor", "Lütfen Bekleyiniz...", true)
            progress.show()
            Timer().schedule(timerTask {
                generateData()
                binding.textViewTopraginNemi.text = topraginNemi
                binding.textViewTopraginSicakligi.text = topraginSicakligi
                binding.textViewHavaninNemi.text = havaninNemi
                binding.textViewHavaninSicakligi.text = havaninSicakligi
                binding.textViewRuzgarDurumuYonu.text = ruzgarDurumuYonu
                binding.textViewRuzgarHizi.text = ruzgarHizi
                progress.cancel()

            }, 2000L)
        }
    }

    private fun generateData() {
        topraginNemi = generateTopraginNemi()
        topraginSicakligi = generateTopraginSicakligi()
        havaninNemi = generateHavaninNemi()
        havaninSicakligi = generateHavaninSicakligi()
        ruzgarDurumuYonu = generateRuzgarDurumuYonu()
        ruzgarHizi = generateRuzgarHizi()
    }

    private fun generateTopraginNemi(): String {
        val randomValue = Random.nextDouble(0.0, 100.0)
        return String.format("%.2f%%", randomValue)
    }

    private fun generateTopraginSicakligi(): String {

        val randomValue = Random.nextDouble(-20.0, 40.0)
        return String.format("%.2f°C", randomValue)
    }

    private fun generateHavaninNemi(): String {
        val randomValue = Random.nextDouble(0.0, 100.0)
        return String.format("%.2f%%", randomValue)
    }

    private fun generateHavaninSicakligi(): String {
        val randomValue = Random.nextDouble(-10.0, 35.0)
        return String.format("%.2f°C", randomValue)
    }

    private fun generateRuzgarDurumuYonu(): String {
        val directions = arrayOf(
            "Kuzey",
            "Kuzey Doğu",
            "Doğu",
            "Güney Doğu",
            "Güney",
            "Güney Batı",
            "Batı",
            "Kuzey Batı"
        )
        return directions[Random.nextInt(0, directions.size - 1)]
    }

    private fun generateRuzgarHizi(): String {
        val randomValue = Random.nextDouble(0.0, 30.0)
        return String.format("%.2f m/s", randomValue)
    }


}