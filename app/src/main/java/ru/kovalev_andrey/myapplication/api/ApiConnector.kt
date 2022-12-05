package ru.kovalev_andrey.myapplication.api

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request


class ApiConnector {

    private val client: OkHttpClient = OkHttpClient()

    fun getQuotes(callback: Callback?) {
        val request = Request.Builder()
            .url("http://mskko2021.mad.hakta.pro/api/quotes")
            .build()
        client.newCall(request).enqueue(callback!!)
    }
}