package app.lahzebar.data.di

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class Anonymous

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class Api
