package com.example.retrofittest.dummyJSON

class ProductRepository {

    suspend fun getProductByID(productID: Int): Product? {
        val request = NetworkLayer.apiClient.getProductByID(productID)

        if (request.isSuccessful) {
            return request.body()!!

        }
        return null
    }
}