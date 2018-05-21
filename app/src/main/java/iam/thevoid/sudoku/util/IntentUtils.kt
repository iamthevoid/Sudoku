package iam.thevoid.sudoku.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import iam.thevoid.sudoku.ui.pages.GameActivity
import kotlin.reflect.KClass

/**
 * Created by iam on 07/09/2017.
 */

const val NEW_TASK_CLOSE_STACK = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
const val NEW_TASK = Intent.FLAG_ACTIVITY_NEW_TASK

val EXTRA_GAME_ID = "${GameActivity::class.java.canonicalName}_EXTRA_GAME_ID"

fun getActivity(context: Context): Activity {
    return when (context) {
        is Activity -> context
        is ContextWrapper -> getActivity(context)
        else -> throw IllegalStateException("Context $context NOT contains activity!")
    }
}

fun startActivity(context: Context, cls: KClass<*>, vararg flags: Int = IntArray(1, { _ -> 0 })) {
    val intent = Intent(context, cls.java)
    flags.forEach { intent.addFlags(it) }
    context.startActivity(intent)
}

fun startActivityCloseStack(context: Context, cls: KClass<*>) {
    startActivity(context, cls, NEW_TASK_CLOSE_STACK)
}

fun startActivityNewTask(context: Context, cls: KClass<*>) {
    startActivity(context, cls, NEW_TASK)
}

fun startGameActivity(context: Context, gameId : Long) {
    val intent = Intent(context, GameActivity::class.java)
    intent.flags = NEW_TASK
    intent.putExtra(EXTRA_GAME_ID, gameId)
    context.startActivity(intent)
 }