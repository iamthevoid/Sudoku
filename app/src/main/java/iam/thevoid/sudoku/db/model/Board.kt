package iam.thevoid.sudoku.db.model

import com.google.gson.annotations.SerializedName
import iam.thevoid.sudoku.db.DbEntity
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.util.toInteger
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import javax.annotation.Nonnull

/**
 * Created by iam on 08/09/2017.
 */
open class Board : RealmObject(), DbEntity {
    @SerializedName("id")
    override var id: Long = 0
    @SerializedName("cells")
    var cells: RealmList<Cell>? = null
        set(value) {
            field = value
            DbHandler.create(this)
        }
    @SerializedName("is_started")
    private var isStarted: Boolean = false
        set(value) {
            field = value
            val sudoku = cellsData!!.substring(82)
            val sudokuGame = cellsData!!.substring(0, 81)
            val cells = RealmList<Cell>()
            var count = 0
            for (index in sudoku.indices) {
                val cell = Cell()
                cell.y = count / 9
                cell.x = count % 9
                cell.number = sudoku.toCharArray().get(index).toInteger()
                cell.insertedNumber = sudokuGame.toCharArray().get(index).toInteger()
                cells.add(cell)
                count++

            }
            this.cells = cells
        }
    @PrimaryKey
    @Nonnull
    @SerializedName("cells_data")
    var cellsData: String? = null
        set(value) {
            field = value
            DbHandler.create(this)
        }

    fun start() {
        isStarted = true
    }

    override fun resolveId(): Long {
        val b = DbHandler.getRealm().where(javaClass).equalTo("cellsData", cellsData).findFirst()
        return b?.id ?: 0
    }

}