package iam.thevoid.sudoku.util

import iam.thevoid.sudoku.db.newdb.dao.ActionDao
import iam.thevoid.sudoku.db.newdb.dao.CellDao
import iam.thevoid.sudoku.db.newdb.dao.GameDao
import iam.thevoid.sudoku.db.newdb.entity.CellEntity
import iam.thevoid.sudoku.db.newdb.entity.GameEntity
import iam.thevoid.sudoku.db.newdb.enums.Difficulty
import iam.thevoid.sudoku.db.newdb.models.Action
import iam.thevoid.sudoku.db.newdb.models.Cell
import iam.thevoid.sudoku.db.newdb.models.Game
import io.reactivex.Flowable
import java.util.*

/**
 * Created by iam on 08/09/2017.
 */

/**
 * =================================================================================================
 * ============================================ ACTION DAO =========================================
 * =================================================================================================
 */

fun ActionDao.all() = selectAll().map { it.map { it as Action } }

fun ActionDao.get(cellId: Long): Flowable<List<Action>> =
        selectForCell(cellId).map { it.map { it as Action } }

/**
 * =================================================================================================
 * ============================================== CELL DAO =========================================
 * =================================================================================================
 */

fun CellDao.create(gameId: Long, contents: String): List<Cell> {
    val cells = ArrayList<CellEntity>()
    val createdCount = countFor(gameId)

    if (createdCount > 0) return cells

    for (num in contents.toCharArray().indices) {
        cells.add(CellEntity(
                contents[num].toIntVal(),
                gameId,
                num % 9,
                num / 9
        ))
    }
    return cells
}

fun CellDao.insert(cells: List<Cell>) = cells.forEach { insert(it) }


fun CellDao.insert(cell: Cell) {
    if (cell is CellEntity) {
        insert(cell)
    }
}

/**
 * =================================================================================================
 * ============================================== GAME DAO =========================================
 * =================================================================================================
 */

fun GameDao.started() = getStarted().map { it.map { it as Game } }

fun GameDao.notStarted() = getNotStarted().map { it as Game }

fun GameDao.create(contents: String) = GameEntity(Difficulty.UNDEFINED, contents) as Game

fun GameDao.createInDb(contents: String): Game {
    val gameEntity = GameEntity(Difficulty.UNDEFINED, contents)
    insert(gameEntity)
    return getLast()
}

fun GameDao.insert(game: Game) {
    if (game is GameEntity) {
        insert(game)
    }
}