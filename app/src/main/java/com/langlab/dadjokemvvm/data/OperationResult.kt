package com.langlab.dadjokemvvm.data

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Error(val error: String?)
}