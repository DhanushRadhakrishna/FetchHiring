package com.example.fetchhiring.Model

import java.io.Serializable

data class Fetch(
    var id : Int = -1,
    var listId : Int = -1,
    var name : String? = ""
) : Serializable
