//
// Created by iam on 30/07/2017.
//


#ifndef SUDOKU_CELL_H
#define SUDOKU_CELL_H


class Cell {

public:

    Cell();

    Cell(int, int, int, int);

    int getNumber();

    int getX();

    int getY();

    int getAttempt();

    void logData();

private:
    int number, x, y, attempt;
};


#endif //SUDOKU_CELL_H
