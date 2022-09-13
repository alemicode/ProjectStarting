package app.lahzebar.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

internal class DataStoreManager @Inject constructor(
    private val context: Context,
) {

    private val Context.dataStore by preferencesDataStore(DATA_STORE_KEY)

    companion object ConstDataStore {
        private const val DATA_STORE_KEY = "data_store_key"
    }
}
