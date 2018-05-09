package iam.thevoid.sudoku.db.newdb.models

import iam.thevoid.sudoku.db.newdb.enums.ActionType

interface Action {
    var id : Long
    val cellId: Long
    val type: ActionType
    val number : Int
}