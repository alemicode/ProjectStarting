package app.lahzebar.data

import app.lahzebar.data.remote.AuthRestApi
import app.lahzebar.domain.api.AuthDataSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class AuthDataSourceImpl @Inject constructor(
    private val authRestApi: AuthRestApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val gson: Gson
) : AuthDataSource
