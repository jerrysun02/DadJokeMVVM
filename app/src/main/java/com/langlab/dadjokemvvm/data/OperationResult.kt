package com.langlab.dadjokemvvm.data

import java.lang.Exception

sealed class OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>()
    data class Error(val exception: Exception?) : OperationResult<Nothing>()
}