package app.lahzebar.di

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ServiceScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ActivityScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class FragmentScope
