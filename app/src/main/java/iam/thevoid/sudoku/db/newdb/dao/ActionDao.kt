package iam.thevoid.sudoku.db.newdb.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import iam.thevoid.sudoku.db.newdb.COLUMN_ACTION_CELL_ID
import iam.thevoid.sudoku.db.newdb.TABLE_NAME_ACTIONS
import iam.thevoid.sudoku.db.newdb.entity.ActionEntity
import iam.thevoid.sudoku.db.newdb.models.Action
import io.reactivex.Flowable

@Dao
interface ActionDao {

    @Query(value = "SELECT * FROM $TABLE_NAME_ACTIONS where $COLUMN_ACTION_CELL_ID = :cellId")
    fun selectForCell(cellId: Long): Flowable<List<ActionEntity>>

    @Query(value = "SELECT * FROM $TABLE_NAME_ACTIONS")
    fun selectAll(): Flowable<List<ActionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(action: ActionEntity)
}