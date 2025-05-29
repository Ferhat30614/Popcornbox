package com.example.poppcornapplicationnew

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.concurrent.TimeUnit


class LikeDao(context: Context) {
    private val dbHelper = PopcornBoxDatabaseHelper(context)

    fun insertOrUpdateLike(entry: Likes) {
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
        val cursor = db.rawQuery("SELECT * FROM likes WHERE contentId = ?", arrayOf(contentId.toString()))
        val result = if (cursor.moveToFirst()) {
            Likes(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                contentId = cursor.getInt(cursor.getColumnIndexOrThrow("contentId")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                type = cursor.getString(cursor.getColumnIndexOrThrow("type")),
                liked = cursor.getInt(cursor.getColumnIndexOrThrow("liked")),
                rating = cursor.getDouble(cursor.getColumnIndexOrThrow("rating")),
                genres = cursor.getString(cursor.getColumnIndexOrThrow("genres"))
            )
        } else null
        cursor.close()
        db.close()
        return result
    }

    fun deleteLike(contentId: Int) {
        val db = dbHelper.writableDatabase
        db.delete("likes", "contentId = ?", arrayOf(contentId.toString()))
        db.close()
    }


   //  likes tablosundaki tüm veriler burada CSV formatında dışarıya aktarılıyor.
    fun exportLikesToCSV(context: Context) {
        val dbHelper = PopcornBoxDatabaseHelper(context)
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM likes", null)

        val csvFile = File(context.getExternalFilesDir(null), "likes_export.csv")
        val writer = FileWriter(csvFile)

        // Başlık
        writer.append("id,contentId,title,type,liked,rating,genres\n")

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val contentId = cursor.getInt(cursor.getColumnIndexOrThrow("contentId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
            val liked = cursor.getInt(cursor.getColumnIndexOrThrow("liked"))
            val rating = cursor.getDouble(cursor.getColumnIndexOrThrow("rating"))
            val genres = cursor.getString(cursor.getColumnIndexOrThrow("genres"))

            writer.append("$id,$contentId,\"$title\",$type,$liked,$rating,\"$genres\"\n")
        }

        writer.flush()
        writer.close()
        cursor.close()
        db.close()

        Log.d("CSV Export", "likes_export.csv oluşturuldu: ${csvFile.absolutePath}")

   }


    fun uploadCSVToFlask(context: Context) {
        val csvFile = File(context.getExternalFilesDir(null), "likes_export.csv")
        if (!csvFile.exists()) {
            Log.e("Upload", "CSV dosyası bulunamadı")
            return
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()


        // MediaType kullanmıyoruz
        val fileBody = okhttp3.RequestBody.create(null, csvFile)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", "likes_export.csv", fileBody)
            .build()

        val request = Request.Builder()
            .url("http://192.168.1.143:5000/upload_csv") // IP doğru olacak
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Upload", "Hata: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body

                if (response.isSuccessful && responseBody != null) {
                    val bodyString = responseBody.string()
                    Log.d("Upload", "CSV başarıyla yüklendi: $bodyString")
                } else {
                    val errorString = responseBody?.string() ?: "Boş cevap"
                    Log.e("Upload", "Sunucu hatası [${response.code}]: $errorString")
                }
            }





        })
    }










}
