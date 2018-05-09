//
// Created by iam on 02/08/2017.
//

#ifndef SUDOKU_BOARDGENERATOR_H
#define SUDOKU_BOARDGENERATOR_H

#define SMALL_SIZE 3

#define WIDTH (SMALL_SIZE * SMALL_SIZE)
#define HEIGHT (SMALL_SIZE * SMALL_SIZE)

#define SHUFFLES 1000


//class BoardGenerator {
//
//public:
///**
//* Generator
//*/
//    BoardGenerator();
//
//    void printBoard(int **);
//
//    int *generate();
//
//private:
//
//    /**
//     * Shuffle
//     */
//
//    int *shuffle(int **a);
//
//    int *swapBigColumns(int a, int b, int **board);
//
//    int *swapBigRows(int a, int b, int **board);
//
//    int *swapColumns(int, int, int **);
//
//    int *swapRows(int, int, int **);
//
//    int *transpose(int **board);
//
//
//    /**
//     * Print helpers
//     */
//    bool isSqrtPrinted(int);
//
//    bool isLastPrinted(int, int);
//};


#endif //SUDOKU_BOARDGENERATOR_H
