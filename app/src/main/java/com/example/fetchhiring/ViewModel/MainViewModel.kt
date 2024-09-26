package com.example.fetchhiring.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.Api.FetchApiInstance
import com.example.fetchhiring.Model.Fetch
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var posts : List<Fetch>
    fun getData()
    {
        viewModelScope.launch {
            val posts = FetchApiInstance.api.getData()
            Log.i("MainViewModel","$posts")
        }

    }
}