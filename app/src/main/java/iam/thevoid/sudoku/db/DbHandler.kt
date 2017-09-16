package iam.thevoid.sudoku.db

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject

/**
 * Created by iam on 08/09/2017.
 */
object DbHandler {

    private val SCHEMA_VERSION = 2

    fun init(context: Context) {
        Realm.init(context)
        val configuration = RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION.toLong())
                .name("main_db.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.compactRealm(configuration)
        Realm.setDefaultConfiguration(configuration)
    }

    fun <T> getDao(cls: Class<T>) : Dao<T> where T : RealmObject, T : DbEntity {
        return Dao(cls)
    }

    fun close() {
        Realm.getDefaultInstance().close()
    }

    fun getRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    fun <T> create(entity: T) where T : RealmObject, T : DbEntity {
        var dao: Dao<T> = Dao(entity.javaClass)
        dao.update(entity)
    }

}