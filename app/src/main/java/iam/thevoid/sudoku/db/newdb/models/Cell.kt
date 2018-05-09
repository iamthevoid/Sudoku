package iam.thevoid.sudoku.db.newdb.models

interface Cell {
    var id : Long
    var gameId: Long
    var number : Int
    var x: Int
    var y: Int
}