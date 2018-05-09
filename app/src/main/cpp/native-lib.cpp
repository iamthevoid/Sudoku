#include <jni.h>
#include <string>

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include "BoardGenerator.h"


extern "C"
JNIEXPORT jstring


JNICALL
Java_iam_thevoid_sudoku_MainActivity_generate(
        JNIEnv *env,
        jobject /* this */) {
//    BoardGenerator boardGenerator;
//    boardGenerator.generate();
    return NULL;
}