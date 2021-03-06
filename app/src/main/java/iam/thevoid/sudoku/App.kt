package iam.thevoid.sudoku

import android.support.multidex.MultiDexApplication
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.db.newdb.DbHelper

/**
 * Created by iam on 08/09/2017.
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DbHandler.init(this)
        DbHelper.init(this)
        // Used to load the 'native-lib' library on application startup.
        System.loadLibrary("native-lib")

    }

}