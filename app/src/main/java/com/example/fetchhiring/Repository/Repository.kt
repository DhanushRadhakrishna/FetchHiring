package com.example.fetchhiring.Repository

import com.example.fetchhiring.Api.FetchApiInstance
import com.example.fetchhiring.Model.Fetch

class Repository {

    //here usually the data is retrieved either from the local data storage or from the remote spurce.
    //Since we are not storing any data in the local storage and getting all the data from the remote source
    //we are directly calling the api get function.
    private lateinit var response : List<Fetch>
    suspend fun getData() : List<Fetch>
    {
        response = FetchApiInstance.api.getData()
        return response
    }

}