package iam.thevoid.sudoku.db.newdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import iam.thevoid.sudoku.db.newdb.dao.ActionDao
import iam.thevoid.sudoku.db.newdb.dao.CellDao
import iam.thevoid.sudoku.db.newdb.dao.GameDao
import iam.thevoid.sudoku.db.newdb.entity.ActionEntity
import iam.thevoid.sudoku.db.newdb.entity.CellEntity
import iam.thevoid.sudoku.db.newdb.entity.GameEntity

@Database(entities = [CellEntity::class, ActionEntity::class, GameEntity::class], version = DB_VERSION)
@TypeConverters(Converters::class, ActionTypeConverter::class, DifficultyConverter::class)
abstract class SudokuDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun cellDao(): CellDao
    abstract fun actionDao(): ActionDao
}