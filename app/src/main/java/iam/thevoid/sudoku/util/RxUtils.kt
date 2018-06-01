package iam.thevoid.sudoku.util

import io.reactivex.disposables.Disposable

fun dispose(disposable: Disposable?) {
    disposable?.dispose()
}

fun dispose(disposable: Disposable?, vararg disposables: Disposable?) {
    dispose(disposable)
    disposables.forEach { dispose(it) }
}