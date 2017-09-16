package iam.thevoid.sudoku.db.model

import com.google.gson.annotations.SerializedName
import iam.thevoid.sudoku.db.DbEntity
import iam.thevoid.sudoku.db.DbHandler
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
    var cells: RealmList<Cell>? = null
        set(value) {
            DbHandler.create(this)
        }
    @SerializedName("is_started")
    var isStarted: Boolean = false
        set(value) {
            field = value
            DbHandler.create(this)
        }
    @PrimaryKey
    @SerializedName("cells_data")
    var cellsData: String? = null
        set(value) {
            DbHandler.create(this)
        }

    override fun resolveId(): Long {
        val b = DbHandler.getRealm().where(javaClass).equalTo("cellsData", cellsData).findFirst()
        return b?.id ?: 0
    }

}