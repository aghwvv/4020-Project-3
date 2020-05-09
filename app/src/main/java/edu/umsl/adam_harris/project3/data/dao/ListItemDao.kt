package edu.umsl.adam_harris.project3.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.umsl.adam_harris.project3.data.entities.ListItem

@Dao
interface ListItemDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * FROM item_table WHERE parent_list_id = :id ORDER BY item_name ASC")
    fun itemsForList(id: Long): LiveData<List<ListItem>>

    @Query("SELECT * FROM item_table WHERE id = :itemId AND parent_list_id = :parentId")
    fun getIem(itemId: Int, parentId: Long): LiveData<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(listItem: ListItem)

    @Query("UPDATE item_table SET item_name = :name WHERE id = :itemId AND parent_list_id = :parentId")
    fun updateName(itemId: Int, parentId: Long, name: String)

    @Query("UPDATE item_table SET price = :price WHERE id = :itemId AND parent_list_id = :parentId")
    fun updatePrice(itemId: Int, parentId: Long, price: Double)

    @Query("UPDATE item_table SET quantity = :quantity WHERE id = :itemId AND parent_list_id = :parentId")
    fun updateQuantity(itemId: Int, parentId: Long, quantity: Int)

    @Query("UPDATE item_table SET is_checked = :checked WHERE id = :itemId AND parent_list_id = :parentId")
    fun updateChecked(itemId: Int, parentId: Long, checked: Boolean)

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Update
    suspend fun update(listItem: ListItem)

    @Delete
    fun delete(listItem: ListItem)
}