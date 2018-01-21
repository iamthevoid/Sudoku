package iam.thevoid.sudoku

import android.support.multidex.MultiDexApplication
import iam.thevoid.sudoku.db.DbHandler

/**
 * Created by iam on 08/09/2017.
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DbHandler.init(this)
    }

}