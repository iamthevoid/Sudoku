package iam.thevoid.sudoku.util

import com.orhanobut.hawk.Hawk

/**
 * Created by iam on 12/09/2017.
 */
object SecurityStore {
    
    fun <T> put(key : String, o : T) {
        Hawk.put(key, o)
    }

    fun <T> delete(key : String, o : T) {
        Hawk.put(key, o)
    }

    fun <T> get(key : String) : T {
        return Hawk.get(key)
    }

    fun <T> get(key : String, defaultValue : T): T {
        return Hawk.get<T>(key, defaultValue)
    }
}