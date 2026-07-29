package ru.edu.qamid

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
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
            if (srcDir.exists()) {
                srcDir.listFiles()?.forEach { file -> saveToMediaStore(file) }
            }
        } catch (_: Exception) {
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
                }
            } else {
                val legacyDir = File("/sdcard/allure-results")
                legacyDir.mkdirs()
                file.copyTo(File(legacyDir, file.name), overwrite = true)
            }
        } catch (_: Exception) {
        }
    }
}
