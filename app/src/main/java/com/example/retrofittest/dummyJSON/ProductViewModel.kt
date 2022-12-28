package com.example.retrofittest.dummyJSON

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _productByIDLiveData = MutableLiveData<Product?>()
    val productByIDLiveData: MutableLiveData<Product?> = _productByIDLiveData

    fun refreshProduct(productID: Int){
        viewModelScope.launch {
            val response = repository.getProductByID(productID)
            _productByIDLiveData.postValue(response)
        }
    }
}