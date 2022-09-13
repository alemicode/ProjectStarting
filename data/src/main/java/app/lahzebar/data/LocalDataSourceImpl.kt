package app.lahzebar.data

import app.lahzebar.data.local.DataStoreManager
import app.lahzebar.domain.api.LocalDataSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class LocalDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val gson: Gson,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataSource
