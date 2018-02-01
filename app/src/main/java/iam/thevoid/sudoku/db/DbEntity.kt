package iam.thevoid.sudoku.db

/**
 * Created by iam on 09/09/2017.
 */
interface DbEntity {

    var id: Long

    fun resolveId() : Long {
        return 0
    }
}