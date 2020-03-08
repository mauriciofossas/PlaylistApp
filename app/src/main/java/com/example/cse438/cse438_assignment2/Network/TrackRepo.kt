package com.example.cse438.cse438_assignment2.Network

import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.ChartPayload
import com.example.cse438.cse438_assignment2.Data.FullTrack
import com.example.cse438.cse438_assignment2.Data.Payload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TrackRepo {
    val service = ApiClient.makeRetrofitService()

    fun getSongs(resBody : MutableLiveData<Payload>, filter: String){
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getSongs(filter)

            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getTopSongs(resBody : MutableLiveData<ChartPayload>){
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getTopSongs()

            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getFullSong(resBody : MutableLiveData<FullTrack>, id: String){
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getSingleTrack(id)

            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }
}