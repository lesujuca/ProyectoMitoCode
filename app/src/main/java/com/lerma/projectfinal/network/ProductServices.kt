package com.lerma.projectfinal.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductServices {
    @GET("product")
    fun getRetrofitProduct(): Call<ProductResponse>

    @POST("user")
    @FormUrlEncoded
    fun saveUser(
        @Field("name")
        name: String,
        @Field("last_name")
        lastName: String,
        @Field("username")
        username: String,
        @Field("password")
        password: String,
        @Field("email")
        email: String,
        @Field("address")
        address: String
    ): Call<UserResponse>

    @POST("user/validate")
    @FormUrlEncoded
    fun validateUser(
        @Field("username")
        username: String,
        @Field("password")
        password: String
    ): Call<UserResponse>
}