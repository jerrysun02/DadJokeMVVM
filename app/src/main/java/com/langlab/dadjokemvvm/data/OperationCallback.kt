package com.langlab.dadjokemvvm.data

interface OperationCallback<T> {
    fun onSuccess(id: String?, joke: String?, status: String?)
    fun onError(error: String?)
}