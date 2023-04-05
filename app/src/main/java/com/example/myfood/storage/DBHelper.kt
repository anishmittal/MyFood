package com.example.myfood.storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "myapp.db"
        private const val DATABASE_VERSION = 1

        // Table name
        private const val TABLE_NAME = "users"

        // Column names
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create the users table
        db?.execSQL("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the users table if it exists
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a new user into the database
    fun insertUser(email: String, password: String): Long {
        val values = ContentValues()
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)

        val db = writableDatabase
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    // Check if a user with the given email and password exists in the database
    fun checkUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?", arrayOf(email, password))
        val result = cursor.count > 0
        cursor.close()
        db.close()
        return result
    }
}
