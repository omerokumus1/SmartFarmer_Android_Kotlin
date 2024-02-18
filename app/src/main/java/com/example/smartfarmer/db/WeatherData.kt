package com.example.smartfarmer.db


data class WeatherData(
    var id: Int = 0,
    var date: String? = null,
    var topraginNemi: String = "0.0",
    var topraginSicakligi: String = "0.0",
    var havaninNemi: String = "0.0",
    var havaninSicakligi: String = "0.0",
    var ruzgarDurumuYonu: String? = null,
    var ruzgarHizi: String = "0.0",
)
