package iam.thevoid.sudoku

import android.app.Application

/**
 * Created by iam on 08/09/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

}