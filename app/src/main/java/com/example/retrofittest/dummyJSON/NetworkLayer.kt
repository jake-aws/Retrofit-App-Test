package com.example.retrofittest.dummyJSON

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
        .addConverterFactory(MoshiConverterFactory.create()).build()

    private val productService: ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }

    val apiClient = ApiClient(productService)
}