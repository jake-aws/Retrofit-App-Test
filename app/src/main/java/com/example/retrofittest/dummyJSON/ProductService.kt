package com.example.retrofittest.dummyJSON

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {
    @GET("products/{product_ID}")
    fun getProductByID(@Path("product_ID") productID:Int):Call<Product>
}