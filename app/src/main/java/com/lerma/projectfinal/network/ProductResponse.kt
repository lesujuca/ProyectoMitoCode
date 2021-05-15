package com.lerma.projectfinal.network

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    var data: ArrayList<ProductDataResponse>,
    var status: Boolean,
    var message: String
)

data class ProductDataResponse(
    var id: Int,
    var name: String,
    @SerializedName("description")
    var des: String,
    var price: Double,
    var urlImage: String,
    var status: Boolean
)