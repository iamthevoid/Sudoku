package iam.thevoid.sudoku.db.newdb.models

import iam.thevoid.sudoku.db.newdb.enums.Difficulty
import java.util.*

interface Game {
    val id: Long
    val difficulty: Difficulty
    val startTime: Date
    val contents: String
    var timeSpent: Long
    var finished: Boolean
    var started: Boolean
    var createdAt: Long
}
