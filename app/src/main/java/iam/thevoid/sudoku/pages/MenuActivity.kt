package iam.thevoid.sudoku.pages

import android.content.Context
import android.content.Intent

/**
 * Created by iam on 12/09/2017.
 */
class MenuActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MenuActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }



}