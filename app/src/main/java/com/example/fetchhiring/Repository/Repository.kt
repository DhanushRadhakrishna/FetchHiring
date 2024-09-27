package com.example.fetchhiring.Repository

import android.content.Context
import com.example.fetchhiring.Api.FetchApiInstance
import com.example.fetchhiring.Model.Fetch

class Repository {

    //here usually the data is retrieved either from the local data storage or from the remote source.
    //Since we are not storing any data in the local storage and getting all the data from the remote source
    //we are directly calling the api get function.
    lateinit var response : List<Fetch>

    //We only want one instance of the repository for the entire app, so creating it with companion object
    companion object {

        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(context: Context): Repository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Repository()
                }
            }
            return INSTANCE!!
        }
    }


    suspend fun getData() : List<Fetch>
    {
        response = FetchApiInstance.api.getData()
        return response
    }

}