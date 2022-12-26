package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.dummyJSON.Product
import com.example.retrofittest.dummyJSON.ProductService
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var productService: ProductService
    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
        productService = retrofit.create(ProductService::class.java)
        getItem((1..100).random())
        binding.nextBtn.setOnClickListener {
            getItem((1..100).random())
        }

    }

    private fun getItem(id: Int) {
        productService.getProductByID(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.i("MainActivity", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Request failed", Toast.LENGTH_SHORT).show()
                    return
                }
                val body = response.body()!!
                Picasso.get().load(body.thumbnail).into(binding.imageView)
                binding.tvTextBrand.text = body.brand
                binding.tvTextDesc.text = body.description
                binding.tvTextCategory.text = body.category
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null Message")
            }
        })
    }
}