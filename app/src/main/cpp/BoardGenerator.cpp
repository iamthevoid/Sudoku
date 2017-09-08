//
// Created by iam on 02/08/2017.
//

#include "BoardGenerator.h"
#include <time.h>
#include <stdlib.h>
#include <string>
#include <android/log.h>


int *BoardGenerator::generate() {
    int board[HEIGHT][WIDTH] = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 1, 5, 6, 4, 8, 9, 7},
            {5, 6, 4, 8, 9, 7, 2, 3, 1},
            {8, 9, 7, 2, 3, 1, 5, 6, 4},
            {3, 1, 2, 6, 4, 5, 9, 7, 8},
            {6, 4, 5, 9, 7, 8, 3, 1, 2},
            {9, 7, 8, 3, 1, 2, 6, 4, 5}
    };

    int **a = new int *[HEIGHT];

    for (int i = 0; i < HEIGHT; i++) {
        a[i] = new int[WIDTH];
        for (int j = 0; j < WIDTH; j++) {
            a[i][j] = board[i][j];
        }
    }

    printBoard(a);

    *a = shuffle(a);

    printBoard(a);

    return *a;
}

int *BoardGenerator::shuffle(int **a) {

    int row1;
    int row2;
    int col1;
    int col2;

    for (int i = 0; i < SHUFFLES; ++i) {
        int type = rand() % 5;
        switch (type) {
            case 0:
                row1 = rand() % SMALL_SIZE;
                row2 = rand() % SMALL_SIZE;
                *a = swapBigRows(row1, row2, a);
                break;
            case 1:
                col1 = rand() % SMALL_SIZE;
                col2 = rand() % SMALL_SIZE;
                *a = swapBigColumns(col1, col2, a);
                break;
            case 2:
                row1 = rand() % HEIGHT;
                row2 = rand() % SMALL_SIZE + row1 / SMALL_SIZE * SMALL_SIZE;
                *a = swapRows(row1, row2, a);
                break;
            case 3:
                col1 = rand() % WIDTH;
                col2 = rand() % SMALL_SIZE + col1 / SMALL_SIZE * SMALL_SIZE;
                *a = swapColumns(col1, col2, a);
                break;
            case 4:
                *a = transpose(a);
                break;
        }

    }

    return *a;
}

void BoardGenerator::printBoard(int **a) {

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
            snprintf(buff, sizeof(buff), "%d%s", a[i][j],
                     (isSqrtPrinted(j) && isLastPrinted(j, WIDTH)) ? "|" : " ");
            buffAsStdStr.append(buff);

        }

        __android_log_write(ANDROID_LOG_INFO, "out", buffAsStdStr.c_str());


        if (isSqrtPrinted(i) && isLastPrinted(i, HEIGHT)) {
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
            std::string div = "";
            for (int k = 0; k < WIDTH; k++) {

                char buff[100];
                snprintf(buff, sizeof(buff), "%s%s", "-",
                         (isSqrtPrinted(k) && isLastPrinted(k, WIDTH)) ? "+" : "-");
                div.append(buff);


            }
            __android_log_write(ANDROID_LOG_INFO, "out", div.c_str());
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
        } else {
            __android_log_print(ANDROID_LOG_INFO, "out", "\n");
        }
    }
}

bool BoardGenerator::isSqrtPrinted(int which) {
    return which % SMALL_SIZE == SMALL_SIZE - 1;
}

bool BoardGenerator::isLastPrinted(int which, int metrics) {
    return which != metrics - 1;
}

BoardGenerator::BoardGenerator() {
    for (int i = 0; i < WIDTH; ++i) {
        for (int j = 0; j < HEIGHT; ++j) {
//            board[i][j] = Cell();
        }
    }
}

int *BoardGenerator::swapRows(int a, int b, int **board) {

    for (int i = 0; i < HEIGHT; ++i) {
        int buff = board[a][i];
        board[a][i] = board[b][i];
        board[b][i] = buff;
    }
    return *board;
}

int *BoardGenerator::swapColumns(int a, int b, int **board) {
    for (int i = 0; i < WIDTH; ++i) {
        int buff = board[i][a];
        board[i][a] = board[i][b];
        board[i][b] = buff;
    }
    return *board;
}

int *BoardGenerator::swapBigColumns(int a, int b, int **board) {
    for (int i = 0; i < WIDTH; ++i) {
        for (int k = 0; k < SMALL_SIZE; k++) {
            int buff = board[i][k + SMALL_SIZE * a];
            board[i][k + SMALL_SIZE * a] = board[i][k + SMALL_SIZE * b];
            board[i][k + SMALL_SIZE * b] = buff;
        }
    }

    return *board;
}

int *BoardGenerator::swapBigRows(int a, int b, int **board) {
    for (int i = 0; i < WIDTH; ++i) {
        for (int k = 0; k < SMALL_SIZE; k++) {
            int buff = board[k + SMALL_SIZE * a][i];
            board[k + SMALL_SIZE * a][i] = board[k + SMALL_SIZE * b][i];
            board[k + SMALL_SIZE * b][i] = buff;
        }
    }

    return *board;
}

int *BoardGenerator::transpose(int **board) {
    for (int i = 0; i < HEIGHT; ++i) {
        for (int j = 0; j < i + 1; ++j) {
            int buff = board[i][j];
            board[i][j] = board[j][i];
            board[j][i] = buff;
        }
    }
    return *board;
}
