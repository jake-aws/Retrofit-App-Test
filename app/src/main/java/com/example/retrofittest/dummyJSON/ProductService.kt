package com.example.retrofittest.dummyJSON

import retrofit2.Call
import retrofit2.http.GET


interface ProductService {
    @GET("products/1")
    fun getProductByID():Call<Any>
}