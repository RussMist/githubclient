package com.russmsit.githubclient

import android.app.Application
import com.russmsit.githubclient.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

@KoinExperimentalAPI
class GithubClientApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidContext(this@GithubClientApplication)

            // Used to get project properties like Api params.
            fileProperties()

            fragmentFactory()
            modules(AppModule.appModules)
        }
    }
}