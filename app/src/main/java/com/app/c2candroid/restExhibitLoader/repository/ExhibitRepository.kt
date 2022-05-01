package com.app.c2candroid.restExhibitLoader.repository

import android.util.Log
import com.app.c2candroid.model.Exhibit
import com.app.c2candroid.model.ExhibitLoader
import com.app.c2candroid.restExhibitLoader.localStorage.ExhibitDao
import com.app.c2candroid.utils.DataState
import com.skydoves.sandwich.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject


@Suppress("BlockingMethodInNonBlockingContext")
class ExhibitRepository
    constructor(
        private val exhibitDao: ExhibitDao,
        private val exhibitRetrofit: ExhibitLoader,
    )
{
        suspend fun getExhibit(): Flow<DataState<List<Exhibit>>> = flow {
            DataState.Loading
            delay(timeMillis = 1000)
            val response = exhibitRetrofit.getExhibitList()
            response.suspendOnSuccess {
                val  networkData = data
                exhibitDao.insert(networkData)

                val cacheBlogs = exhibitDao.get()
                emit(DataState.success(cacheBlogs))
            }
            response.suspendOnError{
                when (statusCode){
                    StatusCode.Unauthorized -> emit(DataState.otherError("token time out"))
                    StatusCode.BadGateway -> emit(DataState.otherError("Something went wrong"))
                    StatusCode.GatewayTimeout -> emit(DataState.otherError("Unable to fetch data, please try again"))
                    StatusCode.BadRequest -> {
                        try {
                            val jObjError = JSONObject(errorBody?.string()!!)
                            emit(DataState.otherError(jObjError.getString("message")))
                        } catch (e: Exception) {
                            emit(DataState.otherError(message()))
                        }
                    }
                    else -> emit(DataState.otherError(message()))
                }
            }
            response.suspendOnException {
                if (exception.message!!.contains("Unable to resolve host")) {
                    emit(DataState.otherError("we are unable to process your request, please try again later"))
                }else{
                    Log.d("message", exception.message!!)
                    emit(DataState.Error(exception))
                }

            }

        }

    }
