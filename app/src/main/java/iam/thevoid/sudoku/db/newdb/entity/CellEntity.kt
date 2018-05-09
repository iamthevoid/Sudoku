package iam.thevoid.sudoku.db.newdb.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import iam.thevoid.sudoku.db.newdb.*
import iam.thevoid.sudoku.db.newdb.models.Cell

@Entity(tableName = TABLE_NAME_CELLS,
        foreignKeys = [
            ForeignKey(
                    entity = GameEntity::class,
                    parentColumns = [COLUMN_CELL_ID],
                    childColumns = [COLUMN_CELL_GAME_ID],
                    onUpdate = CASCADE)
        ]
)
data class CellEntity(
        @ColumnInfo(name = COLUMN_CELL_NUMBER) override var number: Int,
        @ColumnInfo(name = COLUMN_CELL_GAME_ID) override var gameId: Long,
        @ColumnInfo(name = COLUMN_CELL_X) override var x: Int,
        @ColumnInfo(name = COLUMN_CELL_Y) override var y: Int
) : Cell {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_CELL_ID)
    override var id: Long = 0

    override fun toString(): String {
        return "id = $id, num = $number, x = $x, y = $y, gameId = $gameId"
    }
}