package com.example.pokedexpoc

import android.app.Application
import com.example.pokedexpoc.framework.di.DataModule
import com.example.pokedexpoc.framework.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//import org.koin.core.context.GlobalContext.startKoin
//import org.koin.android.ext.android.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger(DEBUG)
            androidContext(this@MyApp)

            //modules(mainModule)
        }

        PresentationModule.load()
        DataModule.load()
    }
}