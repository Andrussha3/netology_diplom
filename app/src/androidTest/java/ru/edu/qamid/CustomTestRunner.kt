package ru.edu.qamid

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import io.qameta.allure.android.runners.AllureAndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import java.io.File

class CustomTestRunner : AllureAndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

    override fun finish(resultCode: Int, results: Bundle?) {
        try {
            val srcDir = File(targetContext.filesDir, "allure-results")
            Log.e("Allure", "finish() srcDir: ${srcDir.absolutePath}, exists: ${srcDir.exists()}")

            if (srcDir.exists()) {
                val files = srcDir.listFiles()
                Log.e("Allure", "Files in srcDir: ${files?.size ?: 0}")
                files?.forEach { Log.e("Allure", "  file: ${it.name} (${it.length()} bytes)") }

                files?.forEach { file ->
                    saveToMediaStore(file)
                }
            }
        } catch (e: Exception) {
            Log.e("Allure", "Error in finish()", e)
        }
        super.finish(resultCode, results)
    }

    private fun saveToMediaStore(file: File) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, file.name)
                    put(MediaStore.Downloads.MIME_TYPE, "application/json")
                    put(MediaStore.Downloads.IS_PENDING, 1)
                }
                val uri = targetContext.contentResolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues
                )
                if (uri != null) {
                    targetContext.contentResolver.openOutputStream(uri)?.use { output ->
                        file.inputStream().use { input -> input.copyTo(output) }
                    }
                    contentValues.clear()
                    contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                    targetContext.contentResolver.update(uri, contentValues, null, null)
                    Log.e("Allure", "Saved to MediaStore.Downloads: ${file.name}")
                } else {
                    Log.e("Allure", "MediaStore insert returned null for: ${file.name}")
                }
            } else {
                val legacyDir = File("/sdcard/allure-results")
                legacyDir.mkdirs()
                file.copyTo(File(legacyDir, file.name), overwrite = true)
                Log.e("Allure", "Saved to /sdcard/allure-results: ${file.name}")
            }
        } catch (e: Exception) {
            Log.e("Allure", "Failed to save ${file.name}: ${e.message}")
        }
    }
}
