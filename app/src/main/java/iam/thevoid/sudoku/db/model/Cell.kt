package iam.thevoid.sudoku.db.model

import com.google.gson.annotations.SerializedName
import iam.thevoid.sudoku.db.DbEntity
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by iam on 07/09/2017.
 */
open class Cell(var x: Int = 0, var y: Int = 0, var number: Int = 0, var insertedNumber: Int = 0) : RealmObject(), DbEntity {

    @PrimaryKey
    override var id: Long = 0

    override fun toString(): String {
        return "x = $x, y = $y, number = $number, insertedNumber = $insertedNumber"
    }
}
