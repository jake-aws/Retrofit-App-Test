package com.example.retrofittest.dummyJSON

import retrofit2.Response

class ApiClient(private val productService: ProductService) {

    suspend fun getProductByID(productID: Int): Response<Product> {
        return productService.getProductByID(productID)

    }
}