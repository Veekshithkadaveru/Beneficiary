package com.example.beneficiary.repository

import android.content.Context
import com.example.beneficiary.model.Beneficiary
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

fun loadJSONFromAsset(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

fun parseBeneficiaries(jsonString: String): List<Beneficiary> {
    val listType = object : TypeToken<List<Beneficiary>>() {}.type
    return Gson().fromJson(jsonString, listType)
}