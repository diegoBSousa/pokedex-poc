package com.example.pokedexpoc

import android.app.Application
import com.example.pokedexpoc.framework.di.mainModule
import com.example.pokedexpoc.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger(DEBUG)
            androidContext(this@MyApp)

            //modules(mainModule)
        }

        PresentationModule.load()
    }
}