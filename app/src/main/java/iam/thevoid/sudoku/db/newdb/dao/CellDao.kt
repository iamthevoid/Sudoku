package iam.thevoid.sudoku.db.newdb.dao

import android.arch.persistence.room.*
import iam.thevoid.sudoku.db.newdb.*
import iam.thevoid.sudoku.db.newdb.entity.CellEntity
import iam.thevoid.sudoku.db.newdb.entity.GameEntity
import iam.thevoid.sudoku.db.newdb.models.Game
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CellDao {
    @Query(value = "SELECT * FROM $TABLE_NAME_CELLS where $COLUMN_CELL_ID = :id LIMIT 1")
    fun get(id: Long): Single<CellEntity>

    @Query(value = "SELECT * FROM $TABLE_NAME_CELLS where $COLUMN_CELL_GAME_ID = :gameId")
    fun getFor(gameId: Long): Flowable<List<CellEntity>>

    @Query(value = "SELECT * FROM $TABLE_NAME_CELLS")
    fun all(): Single<List<CellEntity>>

    @Query(value = "SELECT COUNT(*) FROM $TABLE_NAME_GAMES WHERE $COLUMN_CELL_GAME_ID = :gameId")
    fun countFor(gameId : Long) : Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(cell: CellEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cell: CellEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cell: List<CellEntity>)

}