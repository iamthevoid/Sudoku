package iam.thevoid.sudoku.db.newdb

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by alese_000 on 10.04.2018.
 */

const val DB_VERSION = 13
const val DB_NAME = "sudoku_database.db"

private const val COLUMN_ID = "id"


const val TABLE_NAME_CELLS = "cells"
const val TABLE_NAME_ACTIONS = "actions"
const val TABLE_NAME_GAMES = "games"

// action
const val COLUMN_ACTION_ID = COLUMN_ID
const val COLUMN_ACTION_CELL_ID = "cell_id"
const val COLUMN_ACTION_TYPE = "type"
const val COLUMN_ACTION_TIMESTAMP = "timestamp"
const val COLUMN_ACTION_NUMBER = "number"

// cell
const val COLUMN_CELL_ID = COLUMN_ID
const val COLUMN_CELL_GAME_ID = "game_id"
const val COLUMN_CELL_X = "x"
const val COLUMN_CELL_Y = "y"
const val COLUMN_CELL_NUMBER = "number"

// game
const val COLUMN_GAME_ID = COLUMN_ID
const val COLUMN_GAME_CONTENTS = "contents"
const val COLUMN_GAME_FINISHED = "finished"
const val COLUMN_GAME_STARTED = "started"
const val COLUMN_GAME_CREATED_AT = "created_at"
const val COLUMN_GAME_START_TIME = "start_time"
const val COLUMN_GAME_TIME_SPENT = "time_spent"
const val COLUMN_GAME_DIFFICULTY = "difficulty"

object DbHelper {

    lateinit var database: SudokuDatabase

    fun init(context: Context) {
        database = Room
                .databaseBuilder(context.applicationContext, SudokuDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}
