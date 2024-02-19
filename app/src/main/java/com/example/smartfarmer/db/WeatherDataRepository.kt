package com.example.smartfarmer.db

import android.content.ContentValues
import android.content.Context


class WeatherDataRepository(context: Context?) {
    private val dbHelper: WeatherDataDBHelper

    init {
        dbHelper = WeatherDataDBHelper(context)
    }

    companion object {
        private var instance: WeatherDataRepository? = null
        fun getInstance(context: Context?): WeatherDataRepository {
            if (instance == null) {
                instance = WeatherDataRepository(context)
            }
            return instance!!
        }
    }

    fun insertWeatherData(weatherData: WeatherData) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("date", weatherData.date)
        values.put("topraginNemi", weatherData.topraginNemi)
        values.put("topraginSicakligi", weatherData.topraginSicakligi)
        values.put("havaninNemi", weatherData.havaninNemi)
        values.put("havaninSicakligi", weatherData.havaninSicakligi)
        values.put("ruzgarDurumuYonu", weatherData.ruzgarDurumuYonu)
        values.put("ruzgarHizi", weatherData.ruzgarHizi)
        db.insert("weather_data", null, values)
        db.close()
    }

    val allWeatherData: List<WeatherData>
        get() {
            val weatherDataList: MutableList<WeatherData> = ArrayList()
            val db = dbHelper.readableDatabase
            val cursor = db.query("weather_data", null, null, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val weatherData = WeatherData()
                    weatherData.id = (cursor.getInt(cursor.getColumnIndex("id") ?: 0))
                    weatherData.date = (cursor.getString(cursor.getColumnIndex("date") ?: 0))
                    weatherData.topraginNemi =
                        (cursor.getString(cursor.getColumnIndex("topraginNemi") ?: 0))
                    weatherData.topraginSicakligi =
                        (cursor.getString(cursor.getColumnIndex("topraginSicakligi") ?: 0))
                    weatherData.havaninNemi =
                        (cursor.getString(cursor.getColumnIndex("havaninNemi") ?: 0))
                    weatherData.havaninSicakligi =
                        (cursor.getString(cursor.getColumnIndex("havaninSicakligi") ?: 0))
                    weatherData.ruzgarDurumuYonu =
                        (cursor.getString(cursor.getColumnIndex("ruzgarDurumuYonu") ?: 0))
                    weatherData.ruzgarHizi =
                        (cursor.getString(cursor.getColumnIndex("ruzgarHizi") ?: 0))
                    weatherDataList.add(weatherData)
                } while (cursor.moveToNext())
                cursor.close()
            }
            db.close()
            return weatherDataList
        }
}

