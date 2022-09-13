package app.lahzebar.data

import app.lahzebar.data.remote.RemoteRestApi
import app.lahzebar.domain.api.RemoteDataSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val remoteRestApi: RemoteRestApi,
    private val gson: Gson,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSource
