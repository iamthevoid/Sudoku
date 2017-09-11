package iam.thevoid.sudoku.db

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject

/**
 * Created by iam on 08/09/2017.
 */
object DbHandler {

    val SCHEMA_VERSION = 0

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

    fun getRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    fun <T> create(entity: T) where T : RealmObject, T : DbEntity {
        var dao: Dao<T> = Dao()
        dao.update(entity)
    }

}