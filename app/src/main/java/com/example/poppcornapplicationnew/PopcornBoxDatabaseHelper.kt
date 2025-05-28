package com.example.poppcornapplicationnew

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PopcornBoxDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "PopcornBox.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE likes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                contentId INTEGER,
                title TEXT,
                type TEXT,
                liked INTEGER,
                rating REAL,
                genres TEXT
            )
        """.trimIndent()

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS likes")
        onCreate(db)
    }
}
