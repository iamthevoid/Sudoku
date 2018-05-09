package iam.thevoid.sudoku.db.newdb

import android.arch.persistence.room.TypeConverter
import iam.thevoid.sudoku.db.newdb.enums.ActionType
import iam.thevoid.sudoku.db.newdb.enums.Difficulty
import java.util.*


class Converters {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return value?.time
    }
}

class ActionTypeConverter {
    @TypeConverter
    fun actionTypeToInt(type: ActionType?): Int? {
        if (type == null) {
            return ActionType.UNDEFINED.ordinal
        }
        return type.ordinal
    }

    @TypeConverter
    fun intToActionType(type : Int?): ActionType? {
        if (type == null || type >= ActionType.values().size) {
            return ActionType.UNDEFINED
        }

        return ActionType.values()[type]
    }
}

class DifficultyConverter {
    @TypeConverter
    fun difficultyToInt(difficulty: Difficulty?): Int? {
        if (difficulty == null) {
            return Difficulty.UNDEFINED.ordinal
        }
        return difficulty.ordinal
    }

    @TypeConverter
    fun intToDifficulty(difficulty : Int?): Difficulty? {
        if (difficulty == null || difficulty >= ActionType.values().size) {
            return Difficulty.UNDEFINED
        }

        return Difficulty.values()[difficulty]
    }
}