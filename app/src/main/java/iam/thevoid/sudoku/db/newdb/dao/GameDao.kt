package iam.thevoid.sudoku.db.newdb.dao

import android.arch.persistence.room.*
import iam.thevoid.sudoku.db.newdb.COLUMN_GAME_CREATED_AT
import iam.thevoid.sudoku.db.newdb.COLUMN_GAME_ID
import iam.thevoid.sudoku.db.newdb.TABLE_NAME_GAMES
import iam.thevoid.sudoku.db.newdb.entity.GameEntity
import io.reactivex.Single

@Dao
interface GameDao {
    @Query(value = "SELECT * FROM $TABLE_NAME_GAMES where $COLUMN_GAME_ID = :id LIMIT 1")
    fun get(id: Long): Single<GameEntity>

    @Query(value = "SELECT * FROM $TABLE_NAME_GAMES where $COLUMN_GAME_CREATED_AT = (SELECT MAX($COLUMN_GAME_CREATED_AT) FROM $TABLE_NAME_GAMES)")
    fun getLast(): GameEntity

    @Query("SELECT COUNT(*) FROM $TABLE_NAME_GAMES")
    fun count(): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(game: GameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: GameEntity)

}