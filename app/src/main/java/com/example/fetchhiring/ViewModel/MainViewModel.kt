package com.example.fetchhiring.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.Repository.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository = Repository()
    private val _items: MutableLiveData<List<Fetch>> = MutableLiveData()
    val items : LiveData<List<Fetch>>
        get() = _items

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getData()
    {
        viewModelScope.launch {
            try{
                var responseList  =   repository.getData()
                var filteredList = responseList.filter { !it.name.isNullOrBlank() }
                val sortedList = filteredList.sortedWith(compareBy({ it.listId }, { it.name }))
                _items.value = sortedList
                _errorMessage.value = null
                _isLoading.value = true

            }
            catch (responseError : Exception)
            {
                _errorMessage.value = responseError.message
            }finally {
                _isLoading.value = false
            }

//            Log.i("MainViewModel","$items")

        }

    }
}