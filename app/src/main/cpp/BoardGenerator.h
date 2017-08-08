//
// Created by iam on 02/08/2017.
//

#ifndef SUDOKU_BOARDGENERATOR_H
#define SUDOKU_BOARDGENERATOR_H


#include "Cell.h"
#include <stack>

#define SMALL_SIZE 3

#define WIDTH (SMALL_SIZE * SMALL_SIZE)
#define HEIGHT (SMALL_SIZE * SMALL_SIZE)


class BoardGenerator {

public:
/**
* Generator
*/
    BoardGenerator();

    void generate();

    void printBoard();

private:

    std::stack<Cell> cellStack;

    Cell board[WIDTH][HEIGHT];

    Cell tryPlaceCell(Cell);

    /**
     * Print helpers
     */
    bool isSqrtPrinted(int, int);

    bool isLastPrinted(int, int);

    /**
     * Generate helpers
     */

    int positionInHorizontalLine(int, int);

    int positionInVerticalLine(int, int);

    int xPositionInSquare(int, int, int);

    void increaseX(int *, int *);

    void increaseY(int *, int *);

    void nextSquare(int *, int *);

    int placeCell(Cell c, int step);
};


#endif //SUDOKU_BOARDGENERATOR_H
