package iam.thevoid.sudoku.db

import iam.thevoid.sudoku.db.model.Board
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmQuery
import java.util.concurrent.atomic.AtomicLong


/**
 * Created by iam on 09/09/2017.
 */
class Dao<T> (private val cls: Class<T>) where T : RealmObject, T : DbEntity {

    companion object {
        var incrementalId: AtomicLong = AtomicLong(0)
    }

    fun query() : RealmQuery<T> {
        return DbHandler.getRealm().where(cls)
    }

    fun count(applyQuery : RealmQuery<T>.() -> Unit) : Int {
        val c = query().apply(applyQuery).findAll().count()
        DbHandler.close()
        return c
    }

    fun findFirstAndClose(applyQuery : RealmQuery<T>.() -> Unit): T? {
        val entity = clone(query().apply(applyQuery).findFirst())
        DbHandler.close()
        return entity
    }


    fun prepareCreateOrUpdate(entity: T) {
        resolveId(entity)
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

    private fun resolveId(entity: T) {
        val id = entity.resolveId()
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

    fun clone(objects: Iterable<T>?): List<T>? {
        if (objects == null) {
            return null
        }
        val result = ArrayList<T>()
        for (o in objects) {
            clone(o)?.let { result.add(it) }
        }
        return result
    }

    fun clone(o: T?): T? {
        if (o == null) {
            return null
        }
        if (!(RealmObject.isManaged(o) && RealmObject.isValid(o))) {
            return o
        }
        val realm = DbHandler.getRealm()
        val t = realm.copyFromRealm(o)
        realm.close()
        return t
    }
}