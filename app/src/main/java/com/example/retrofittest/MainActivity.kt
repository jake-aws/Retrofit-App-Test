package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofittest.dummyJSON.ProductService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
        val productsCompatActivity: ProductService = retrofit.create(ProductService::class.java)

        productsCompatActivity.getProductByID().enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                Log.i("MainActivity", response.toString())

            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null Message")
            }
        })
    }
}