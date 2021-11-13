package com.langlab.dadjokemvvm.data

import android.util.Log
import com.langlab.dadjokemvvm.model.Joke
import com.langlab.dadjokemvvm.model.JokeDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeRemoteDataSource(apiClient: ApiClient) : JokeDataSource {

    private var call: Call<JokeResponse>? = null
    private var service = apiClient.build()

    override fun retrieveJokes(callback: OperationCallback<Joke>) {
        call = service?.jokes()
        call?.enqueue(object : Callback<JokeResponse> {
            override fun onFailure(call: Call<JokeResponse>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(call: Call<JokeResponse>, response: Response<JokeResponse>) {
                Log.d("ddd", "response="+response.body().toString())
                response.body()?.let {
                    callback.onSuccess(it.id, it.joke, it.status)
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}