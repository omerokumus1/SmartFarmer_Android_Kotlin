package com.example.smartfarmer

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.smartfarmer.databinding.ActivitySavedDataBinding
import com.example.smartfarmer.db.WeatherData
import com.example.smartfarmer.db.WeatherDataRepository


class SavedDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedDataBinding
    private lateinit var weatherDataRepository: WeatherDataRepository
    private var allWeatherData: List<WeatherData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBar()
        setActionBar()
        weatherDataRepository = WeatherDataRepository.getInstance(applicationContext)
        setAdapter()

        binding.averageBtn.setOnClickListener {
            val averageTopraginNemi = getAverageTopraginNemi()
            val averageTopraginSicakligi = getAverageTopraginSicakligi()
            val averageHavaninNemi = getAverageHavaninNemi()
            val averageHavaninSicakligi = getAverageHavaninSicakligi()
            val averageRuzgarHizi = getAverageRuzgarHizi()
            AlertDialog.Builder(this)
                .setTitle("Ortalama Değerler")
                .setMessage(
                    "Toprağın Nemi: $averageTopraginNemi%\n" +
                            "Toprağın Sıcaklığı: $averageTopraginSicakligi°C\n" +
                            "Havanın Nemi: $averageHavaninNemi%\n" +
                            "Havanın Sıcaklığı: $averageHavaninSicakligi°C\n" +
                            "Rüzgar Hızı: $averageRuzgarHizi m/s\n"
                )
                .setPositiveButton("Tamam") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    private fun getAverageRuzgarHizi(): String {
        var averageRuzgarHizi = 0.0
        for (d in allWeatherData!!) {
            val ruzgarHizi = d.ruzgarHizi.replace("m/s", "").toDoubleOrNull() ?: 0.0
            averageRuzgarHizi += ruzgarHizi
        }
        return String.format("%.2f", averageRuzgarHizi/allWeatherData!!.size)
    }

    private fun getAverageHavaninSicakligi(): String {
        var averageHavaninSicakligi = 0.0
        for (d in allWeatherData!!) {
            val havaninSicakligi = d.havaninSicakligi.replace("°C", "").toDoubleOrNull() ?: 0.0
            averageHavaninSicakligi += havaninSicakligi
        }
        return String.format("%.2f", averageHavaninSicakligi/allWeatherData!!.size)
    }

    private fun getAverageHavaninNemi(): String {
        var averageHavaninNemi = 0.0
        for (d in allWeatherData!!) {
            val havaninNemi = d.havaninNemi.replace("%", "").toDoubleOrNull() ?: 0.0
            averageHavaninNemi += havaninNemi
        }
        return String.format("%.2f", averageHavaninNemi/allWeatherData!!.size)
    }

    private fun getAverageTopraginSicakligi(): String {
        var averageTopraginSicakligi = 0.0
        for (d in allWeatherData!!) {
            val topraginSicakligi = d.topraginSicakligi.replace("°C", "").toDoubleOrNull() ?: 0.0
            averageTopraginSicakligi += topraginSicakligi
        }
        return String.format("%.2f", averageTopraginSicakligi/allWeatherData!!.size)
    }

    private fun getAverageTopraginNemi(): String {
        var averageTopraginNemi = 0.0
        for (d in allWeatherData!!) {
            val topraginNemi = d.topraginNemi.replace("%", "").toDoubleOrNull() ?: 0.0
            averageTopraginNemi += topraginNemi
        }
        return String.format("%.2f", averageTopraginNemi/allWeatherData!!.size)
    }


    private fun setAdapter() {
        allWeatherData = weatherDataRepository.allWeatherData
        val adapter = SavedDataAdapter()
        binding.recyclerView.adapter = adapter
        adapter.list = allWeatherData!!
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
}