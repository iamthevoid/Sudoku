//
// Created by iam on 30/07/2017.
//

#include <android/log.h>
#include "Cell.h"

Cell::Cell() {
    x = 0;
    y = 0;
    number = 0;
    attempt = 0;
}

Cell::Cell(int x, int y, int attempt, int number) {
    this->x = x;
    this->y = y;
    this->attempt = attempt;
    this->number = number;
}

int Cell::getNumber() {
    return number;
}

int Cell::getAttempt() {
    return attempt;
}

int Cell::getY() {
    return y;
}

int Cell::getX() {
    return x;
}

void Cell::logData() {
    __android_log_print(ANDROID_LOG_INFO, "out", "(x = %d, y = %d, getNumber = %d)\n", x, y, number);
}
