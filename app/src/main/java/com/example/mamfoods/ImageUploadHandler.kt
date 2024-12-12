package com.example.mamfoods

import android.graphics.Bitmap
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.ByteArrayOutputStream
import java.io.IOException
import org.json.JSONObject



class ImageUploadHandler {

    private val cloudinaryUrl = "https://api.cloudinary.com/v1_1/dogujzprq/image/upload"
    private val uploadPreset = "Mamfoods"  // Upload preset yang sudah Anda buat di Cloudinary

    // Fungsi untuk upload gambar
    fun uploadImage(bitmap: Bitmap, callback: (String) -> Unit) {
        val byteArray = convertBitmapToByteArray(bitmap)

        // Membuat RequestBody untuk multipart
        val mediaType = "image/jpeg".toMediaType()
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", "image.jpg", RequestBody.create(mediaType, byteArray))
            .addFormDataPart("upload_preset", uploadPreset)
            .build()

        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()

        // Mengirimkan request menggunakan OkHttp
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback("Upload failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    val imageUrl = extractImageUrlFromResponse(jsonResponse)
                    callback(imageUrl)
                } else {
                    callback("Upload failed with status: ${response.code}")
                }
            }
        })
    }

    // Fungsi untuk mengkonversi Bitmap menjadi ByteArray
    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    // Fungsi untuk mengambil URL gambar dari response JSON
    private fun extractImageUrlFromResponse(response: String?): String {
        return try {
            val jsonObject = JSONObject(response)
            jsonObject.getString("secure_url")  // Mendapatkan URL gambar yang aman (HTTPS)
        } catch (e: Exception) {
            "Error extracting image URL"
        }
    }
}
