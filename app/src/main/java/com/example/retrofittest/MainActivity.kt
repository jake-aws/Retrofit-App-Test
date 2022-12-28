package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.dummyJSON.ProductViewModel
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val productViewModel: ProductViewModel by lazy {
        ViewModelProvider(this)[ProductViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getItem((1..100).random())
        binding.nextBtn.setOnClickListener {
            getItem((1..100).random())
        }

        productViewModel.productByIDLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Request failed",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            Picasso.get().load(response.thumbnail).into(binding.imageView)
            binding.tvTextBrand.text = response.brand
            binding.tvTextDesc.text = response.description
            binding.tvTextCategory.text = response.category
        }

    }

    private fun getItem(id: Int) {
        productViewModel.refreshProduct(id)

    }
}