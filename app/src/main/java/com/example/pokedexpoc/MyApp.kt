package com.example.pokedexpoc

import android.app.Application
import com.example.pokedexpoc.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger()
            androidContext(this@MyApp)

            modules(mainModule)
        }
    }
}