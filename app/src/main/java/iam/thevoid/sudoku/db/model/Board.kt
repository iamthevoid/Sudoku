package iam.thevoid.sudoku.db.model

import com.google.gson.annotations.SerializedName
import iam.thevoid.sudoku.db.DbEntity
import iam.thevoid.sudoku.db.DbHandler
import iam.thevoid.sudoku.util.toIntVal
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by iam on 08/09/2017.
 */
open class Board : RealmObject(), DbEntity {

    @SerializedName("id")
    override var id: Long = 0

    @SerializedName("cells")
    lateinit var cells: RealmList<Cell>

    @PrimaryKey
    @SerializedName("cells_data")
    lateinit var cellsData: String

    @SerializedName("is_started")
    private var isStarted: Boolean = false
        set(value) {
            field = value

            // Game. Some numbers are zeroes
            val sudokuGame = cellsData.substring(0, 81)
            val gameNumbers = sudokuGame.toCharArray()

            // Solved Game. All numbers as they is
            val sudokuNums = cellsData.substring(82, cellsData.length - 1)
            val nums = sudokuNums.toCharArray()

            val cells = RealmList<Cell>()

            sudokuNums.indices.mapTo(cells) {
                Cell(
                        it / 9,
                        it % 9,
                        nums[it].toIntVal(),
                        gameNumbers[it].toIntVal()
                )
            }

            this.cells = cells

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