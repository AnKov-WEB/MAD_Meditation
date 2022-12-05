package ru.kovalev_andrey.myapplication.models

import android.net.Uri
import org.json.JSONException
import org.json.JSONObject


class MainModel(private val jsonObject: JSONObject) {

    @Throws(JSONException::class)
    fun getTitle(): String {
        return if (jsonObject.has("title")) {
            jsonObject.getString("title")
        } else {
            "Ошибка получения данных"
        }
    }

    @Throws(JSONException::class)
    fun getBody(): String {
        return if (jsonObject.has("description")) {
            jsonObject.getString("description")
        } else {
            "Ошибка получения данных"
        }
    }

    @Throws(JSONException::class)
    fun getImage(): Uri? {
        return if (jsonObject.has("image")) {
            Uri.parse(jsonObject.getString("image"))
        } else {
            null
        }
    }
}