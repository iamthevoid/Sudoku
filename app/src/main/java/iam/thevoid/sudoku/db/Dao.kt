package iam.thevoid.sudoku.db

import iam.thevoid.sudoku.R
import iam.thevoid.sudoku.db.model.Board
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmQuery
import java.lang.reflect.Field
import java.util.concurrent.atomic.AtomicLong
import kotlin.reflect.KVisibility
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties


/**
 * Created by iam on 09/09/2017.
 */
class Dao<T>(private val cls: Class<T>) where T : RealmObject, T : DbEntity {

    companion object {
        var incrementalId: AtomicLong = AtomicLong(0)
    }

    fun query(): RealmQuery<T> {
        return DbHandler.getRealm().where(cls)
    }

    infix fun findFirstAndClose(applyQuery: RealmQuery<T>.() -> Unit): T? {
        val entity = clone(query().apply(applyQuery).findFirst())
        DbHandler.close()
        return entity
    }


    fun prepareCreateOrUpdate(entity: T) {
        resolveId(entity)
        prepareCreateOrUpdateInternalItems(entity)
    }

    @Suppress("unchecked_cast")
    private fun prepareCreateOrUpdateInternalItems(entity: T) {
        entity.javaClass.declaredFields.forEach {
            val kClass = it.type.kotlin
            when {
                kClass.isSubclassOf(DbEntity::class) -> {
                    prepareCreateOrUpdate(it.get(entity) as T)
                }
                kClass.isSubclassOf(RealmList::class) -> {
                    val list : RealmList<*>? = it.get(entity) as? RealmList<*>
                    list?.forEach {
                        if (it is DbEntity && it is RealmObject)
                            prepareCreateOrUpdate(it as T)
                    }
                }
            }
        }
    }

    private fun resolveId(entity: T) {
        if (entity.resolveId() == 0L) {
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