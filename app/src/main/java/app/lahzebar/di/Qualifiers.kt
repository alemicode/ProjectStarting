package app.lahzebar.di

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ServiceContext

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ActivityContext
