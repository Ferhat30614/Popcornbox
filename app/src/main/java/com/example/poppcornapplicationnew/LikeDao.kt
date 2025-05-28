package com.example.poppcornapplicationnew

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class LikeDao(context: Context) {
    private val dbHelper = PopcornBoxDatabaseHelper(context)

    fun insertLike(entry: Likes) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("contentId", entry.contentId)
            put("title", entry.title)
            put("type", entry.type)
            put("liked", entry.liked)
            put("rating", entry.rating)
            put("genres", entry.genres)
        }
        db.insertWithOnConflict("likes", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    fun getLikeStatus(contentId: Int): Likes? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "likes", null, "contentId = ?", arrayOf(contentId.toString()),
            null, null, null
        )

        var result: Likes? = null
        if (cursor.moveToFirst()) {
            result = Likes(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                contentId = cursor.getInt(cursor.getColumnIndexOrThrow("contentId")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                type = cursor.getString(cursor.getColumnIndexOrThrow("type")),
                liked = cursor.getInt(cursor.getColumnIndexOrThrow("liked")),
                rating = cursor.getDouble(cursor.getColumnIndexOrThrow("rating")),
                genres = cursor.getString(cursor.getColumnIndexOrThrow("genres"))
            )
        }

        cursor.close()
        db.close()
        return result
    }

    fun deleteLike(contentId: Int) {
        val db = dbHelper.writableDatabase
        db.delete("likes", "contentId = ?", arrayOf(contentId.toString()))
        db.close()
    }

    fun getAllLikes(): List<Likes> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM likes", null)
        val list = mutableListOf<Likes>()

        while (cursor.moveToNext()) {
            list.add(
                Likes(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    contentId = cursor.getInt(cursor.getColumnIndexOrThrow("contentId")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    type = cursor.getString(cursor.getColumnIndexOrThrow("type")),
                    liked = cursor.getInt(cursor.getColumnIndexOrThrow("liked")),
                    rating = cursor.getDouble(cursor.getColumnIndexOrThrow("rating")),
                    genres = cursor.getString(cursor.getColumnIndexOrThrow("genres"))
                )
            )
        }

        cursor.close()
        db.close()
        return list
    }
}
