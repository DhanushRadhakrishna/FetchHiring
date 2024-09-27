package com.example.fetchhiring.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.Repository.Repository

class DetailViewModel(var listId : Int,var context : Context) : ViewModel() {

    private val repository = Repository.getRepository(context)
    private val _items: MutableLiveData<List<Fetch>> = MutableLiveData()
    val items : LiveData<List<Fetch>>
        get() = _items

    var tempItems = mutableListOf<Fetch>()
    fun getDataFromRepository()
    {
        var allItems = repository.response
        var filteredList = allItems.filter { !it.name.isNullOrBlank() }

        filteredList.forEach { item ->
            if(item.listId == listId)
            {
                tempItems.add(item)
            }
        }
        val sortedList = tempItems.sortedWith(compareBy({ it.name }))
        _items.value = sortedList
    }

}
class DetailViewModelFactory(private val listID : Int,private val context : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(listID,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        return super.create(modelClass)
    }

}