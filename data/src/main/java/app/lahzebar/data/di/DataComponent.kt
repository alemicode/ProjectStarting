package app.lahzebar.data.di

import android.content.Context
import app.lahzebar.domain.api.AuthDataSource
import app.lahzebar.domain.api.LocalDataSource
import app.lahzebar.domain.api.RemoteDataSource
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DataModule::class,
        NetworkModule::class
    ]
)
interface DataComponent {

    fun remoteDataSource(): RemoteDataSource
    fun authDataSource(): AuthDataSource
    fun localDataSource(): LocalDataSource

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): DataComponent
    }
}
