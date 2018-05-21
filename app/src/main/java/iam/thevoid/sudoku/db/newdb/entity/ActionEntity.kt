package iam.thevoid.sudoku.db.newdb.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import iam.thevoid.sudoku.db.newdb.*
import iam.thevoid.sudoku.db.newdb.enums.ActionType
import iam.thevoid.sudoku.db.newdb.models.Action
import java.util.*

@Entity(tableName = TABLE_NAME_ACTIONS,
        foreignKeys = [
            ForeignKey(
                    entity = GameEntity::class,
                    parentColumns = [COLUMN_ACTION_ID],
                    childColumns = [COLUMN_ACTION_CELL_ID],
                    onUpdate = CASCADE,
                    onDelete = CASCADE
            )
        ]
)
@TypeConverters(ActionTypeConverter::class)
class ActionEntity(
        @ColumnInfo(name = COLUMN_ACTION_CELL_ID) override val cellId: Long,
        @ColumnInfo(name = COLUMN_ACTION_TYPE) override var type: ActionType,
        @ColumnInfo(name = COLUMN_ACTION_NUMBER) override val number: Int
) : Action {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ACTION_ID)
    override var id: Long = 0

    @ColumnInfo(name = COLUMN_ACTION_TIMESTAMP)
    override val timestamp: Date = Date()

    override fun toString(): String {
        return "type = ${type.name}, cellId = $cellId, num = $number"
    }
}