package com.example.retrofittest.dummyJSON

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {
    @GET("products/{product_ID}")
    suspend fun getProductByID(@Path("product_ID") productID: Int): Response<Product>
}