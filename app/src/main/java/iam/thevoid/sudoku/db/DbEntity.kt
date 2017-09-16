package iam.thevoid.sudoku.db

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by iam on 09/09/2017.
 */
open interface DbEntity {

    var id: Long

    fun resolveId() : Long {
        return 0
    }
}