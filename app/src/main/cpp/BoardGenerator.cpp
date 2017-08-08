//
// Created by iam on 02/08/2017.
//

#include "BoardGenerator.h"
#include <time.h>
#include <stdlib.h>
#include <string>
#include <android/log.h>

void BoardGenerator::generate() {
    // Creating random SUDOKU
    srand((unsigned int) time(NULL));

    for (int step = 0; step < WIDTH * HEIGHT;) {
        // number (1-9)
        int number = step % WIDTH + 1;
        // rand x
        int nextX = rand() % WIDTH;
        // rand y
        int nextY = rand() % HEIGHT;

        step = placeCell(Cell(nextX, nextY, 0, number), step);
        step++;
    }
}

int BoardGenerator::placeCell(Cell c, int step) {

    Cell cell = tryPlaceCell(c);

    if (cell.getAttempt() < WIDTH * HEIGHT) {
        cellStack.push(cell);
        board[cell.getY()][cell.getX()] = cell;
        printBoard();
    } else {
        Cell poppedCell = cellStack.top();
        cellStack.pop();
        board[poppedCell.getY()][poppedCell.getX()] = Cell();
        placeCell(poppedCell, 0);
        step--;
    }

    return step;
}

Cell BoardGenerator::tryPlaceCell(Cell cell) {

    int nextX = cell.getX(), nextY = cell.getY();

    // attempt. Increase to 81 if it is needed. Avoid endless cycle;
    int attempt = cell.getAttempt();

    if (attempt != 0) {
        increaseX(&nextX, &nextY);
    }

    int number = cell.getNumber();

    // Variable to find offset of attempts for not retry any attempts
    int curX;

    while (attempt++ < WIDTH * HEIGHT) {

        // Number already placed in this Cell
        if (board[nextY][nextX].getNumber() != 0) {
            increaseX(&nextX, &nextY);
            continue;
        }

        // Check if there are no same numbers in horizontal line
        if (positionInHorizontalLine(nextY, number) != -1) {
            curX = nextX;
            attempt += WIDTH - curX - 1;
            increaseY(&nextX, &nextY);
            continue;
        }


        if (xPositionInSquare(nextX, nextY, number) != -1) {
            curX = nextX;
            nextSquare(&nextX, &nextY);
            attempt += nextX == 0 ? WIDTH - curX : nextX - curX;
            continue;
        }

        if (positionInVerticalLine(nextX, number) != -1) {
            increaseX(&nextX, &nextY);
            continue;
        }

        if (attempt < WIDTH * HEIGHT) break;
    }

    __android_log_print(ANDROID_LOG_INFO, "tag", "%d", attempt);


    return Cell(nextX, nextY, attempt, number);
}

//
// ------------------------- STATEMENTS -----------------------------------
//

int BoardGenerator::positionInHorizontalLine(int y, int value) {
    for (int i = 0; i < WIDTH; ++i) {
        if (board[y][i].getNumber() == value) {
            return i;
        }
    }

    return -1;
}

int BoardGenerator::positionInVerticalLine(int x, int value) {

    for (int i = 0; i < HEIGHT; ++i) {
        if (board[i][x].getNumber() == value) {
            return i;
        }
    }

    return -1;
}

int BoardGenerator::xPositionInSquare(int nextX, int nextY, int value) {
    int startX = nextX / SMALL_SIZE * SMALL_SIZE;
    int startY = nextY / SMALL_SIZE * SMALL_SIZE;

    for (int i = startY; i < startY + SMALL_SIZE; ++i) {
        for (int j = startX; j < startX + SMALL_SIZE; ++j) {

            if (board[i][j].getNumber() == value) {
                return j;
            }
        }
    }

    return -1;
}

//
// -------------------------- PRINTER -------------------------------------
//

void BoardGenerator::printBoard() {

    __android_log_write(ANDROID_LOG_INFO, "out", ".");


    std::string div = "";
    for (int k = 0; k < WIDTH; k++) {

        char buff[100];
        snprintf(buff, sizeof(buff), "%s", "--");
        div.append(buff);


    }
    __android_log_write(ANDROID_LOG_INFO, "out", div.c_str());

    for (int i = 0; i < HEIGHT; i++) {
        std::string buffAsStdStr = "";

        for (int j = 0; j < WIDTH; j++) {
            char buff[100];
            snprintf(buff, sizeof(buff), "%d%s", board[i][j].getNumber(),
                     (isSqrtPrinted(j, WIDTH) && isLastPrinted(j, WIDTH)) ? "|" : " ");
            buffAsStdStr.append(buff);

        }

        __android_log_write(ANDROID_LOG_INFO, "out", buffAsStdStr.c_str());


        if (isSqrtPrinted(i, HEIGHT) && isLastPrinted(i, HEIGHT)) {
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
            std::string div = "";
            for (int k = 0; k < WIDTH; k++) {

                char buff[100];
                snprintf(buff, sizeof(buff), "%s%s", "-",
                         (isSqrtPrinted(k, WIDTH) && isLastPrinted(k, WIDTH)) ? "+" : "-");
                div.append(buff);


            }
            __android_log_write(ANDROID_LOG_INFO, "out", div.c_str());
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
        } else {
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
        }
    }
}

bool BoardGenerator::isSqrtPrinted(int which, int metrics) {
    return which % SMALL_SIZE == SMALL_SIZE - 1;
}

bool BoardGenerator::isLastPrinted(int which, int metrics) {
    return which != metrics - 1;
}

void BoardGenerator::increaseX(int *nextX, int *nextY) {
    (*nextX)++;
    *nextX = (*nextX == WIDTH) ? 0 : *nextX;
    *nextY = (*nextX == 0) ? *nextY + 1 : *nextY;
    (*nextY) = (*nextY == HEIGHT) ? 0 : *nextY;
}

void BoardGenerator::increaseY(int *nextX, int *nextY) {
    (*nextY)++;
    *nextY = (*nextY == HEIGHT) ? 0 : *nextY;
    (*nextX) = 0;
}


void BoardGenerator::nextSquare(int *nextX, int *nextY) {
    (*nextX) = (*nextX / SMALL_SIZE + 1) * SMALL_SIZE;
    (*nextX) = (*nextX == WIDTH) ? 0 : *nextX;
    (*nextY) = (*nextX == 0) ? (*nextY + 1) : *nextY;
    (*nextY) = (*nextY == HEIGHT) ? 0 : *nextY;
}

BoardGenerator::BoardGenerator() {
    for (int i = 0; i < WIDTH; ++i) {
        for (int j = 0; j < HEIGHT; ++j) {
            board[i][j] = Cell();
        }
    }
}
