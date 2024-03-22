package com.sample.moviedb.utils

sealed class Resource<T>(val data : T?=null,val message: String? = null) {
    class Loading<T>(val isLoading : Boolean) : Resource<T>(null)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T?=null,message: String?) : Resource<T>(data,message)
}