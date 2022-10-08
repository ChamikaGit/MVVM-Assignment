package com.digikraft.bikestation.data.repository.base

import android.util.Log
import com.digikraft.bikestation.BuildConfig
import com.digikraft.bikestation.utils.Constants.TAG
import com.digikraft.bikestation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository (){

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    Resource.Error(errorMessage = response.message())
                }
            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                if (BuildConfig.DEBUG){
                    Log.e(TAG, "safeApiCall:"+e.message.toString())
                }
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }
}