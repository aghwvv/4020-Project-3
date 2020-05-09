package edu.umsl.adam_harris.project3.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.umsl.adam_harris.project3.data.entities.ShoppingList

/**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */

@Dao
interface ShoppingDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from `list_table` ORDER BY name ASC")
    fun getLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM `list_table` WHERE id = :id")
    fun getList(id: Long): ShoppingList

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(shoppingList: ShoppingList)

    @Query("DELETE FROM list_table")
    fun deleteAll()

//    @Query("UPDATE `list_table` SET name = :name WHERE id = :id")
//    suspend fun updateName(id: Long, name: String)
//
//    @Query("DELETE FROM `list_table` WHERE id = :id")
//    suspend fun delete(id: Long)

    @Query("SELECT id from list_table WHERE name = 'Hello'")
    fun getElementId(): Long //TODO(Delete later)
}