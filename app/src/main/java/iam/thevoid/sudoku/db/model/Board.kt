package iam.thevoid.sudoku.db.model

import com.google.gson.annotations.SerializedName
import iam.thevoid.sudoku.db.DbEntity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by iam on 08/09/2017.
 */
open class Board : RealmObject(), DbEntity {
    @PrimaryKey
    override var id: Long = 0
    @SerializedName("cells")
    var cells: RealmList<Cell>? = null

}