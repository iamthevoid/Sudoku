package iam.thevoid.sudoku.db

import iam.thevoid.sudoku.db.Dao.Companion.incrementalId
import io.realm.RealmList
import io.realm.RealmObject
import java.util.concurrent.atomic.AtomicLong
import java.lang.reflect.AccessibleObject.setAccessible
import kotlin.reflect.jvm.isAccessible


/**
 * Created by iam on 09/09/2017.
 */
class Dao<T> where T : RealmObject, T : DbEntity {

    companion object {
        var incrementalId : AtomicLong = AtomicLong(0)
    }

    fun prepareCreateOrUpdate(entity: T) {
        responseId(entity)
        prepareCreateOrUpdateInternalItems(entity)
    }

    private fun prepareCreateOrUpdateInternalItems(entity: T) {
        var cls = entity.javaClass
        for (field in cls.declaredFields) {
            if (field.type is DbEntity) {
                prepareCreateOrUpdate(entity)
            } else if (field.type == RealmList<T>().javaClass) {
                field.isAccessible = true

                val value = field.get(entity) as RealmList<T>
                for (entity in value) {
                    if (entity is DbEntity) {
                        prepareCreateOrUpdate(entity)
                    }
                }
            }
        }
    }

    private fun responseId(entity : T) {
        val id = entity.id
        if (id == 0L) {
            entity.id = incrementalId.incrementAndGet()
        }
    }

    fun update(entity: T) {
        prepareCreateOrUpdate(entity)
        DbHandler.getRealm().beginTransaction()
        DbHandler.getRealm().insertOrUpdate(entity)
        DbHandler.getRealm().commitTransaction()
        DbHandler.getRealm().close()
    }

}