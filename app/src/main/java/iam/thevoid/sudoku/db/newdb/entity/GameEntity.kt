package iam.thevoid.sudoku.db.newdb.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import iam.thevoid.sudoku.db.newdb.*
import iam.thevoid.sudoku.db.newdb.enums.Difficulty
import iam.thevoid.sudoku.db.newdb.models.Game
import java.util.*

@Entity(tableName = TABLE_NAME_GAMES)
@TypeConverters(DifficultyConverter::class, Converters::class)
data class GameEntity(
        @ColumnInfo(name = COLUMN_GAME_DIFFICULTY) override var difficulty: Difficulty,
        @ColumnInfo(name = COLUMN_GAME_CONTENTS) override val contents: String
) : Game {

    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0

    @ColumnInfo(name = COLUMN_GAME_TIME_SPENT)
    override var timeSpent: Long = 0

    @ColumnInfo(name = COLUMN_GAME_START_TIME)
    override var startTime: Date = Date(0)

    @ColumnInfo(name = COLUMN_GAME_FINISHED)
    override var finished: Boolean = false

    @ColumnInfo(name = COLUMN_GAME_STARTED)
    override var started: Boolean = false

    @ColumnInfo(name = COLUMN_GAME_CREATED_AT)
    override var createdAt: Long = Date().time
}