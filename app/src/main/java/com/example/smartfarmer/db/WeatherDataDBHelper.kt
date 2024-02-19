package com.example.smartfarmer.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class WeatherDataDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE weather_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "topraginNemi REAL," +
                "topraginSicakligi REAL," +
                "havaninNemi REAL," +
                "havaninSicakligi REAL," +
                "ruzgarDurumuYonu TEXT," +
                "ruzgarHizi REAL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades if needed
    }

    companion object {
        private const val DATABASE_NAME = "weather_database"
        private const val DATABASE_VERSION = 1
    }
}

