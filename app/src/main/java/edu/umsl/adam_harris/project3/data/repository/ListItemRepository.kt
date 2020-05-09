package edu.umsl.adam_harris.project3.data.repository

import androidx.annotation.WorkerThread
import edu.umsl.adam_harris.project3.data.dao.ListItemDao
import edu.umsl.adam_harris.project3.data.entities.ListItem

class ListItemRepository(private val listItemDao: ListItemDao) {
//    val allItems: LiveData<List<ListItem>> = listItemDao.testGetItems()

    fun itemsForList(id: Long) = listItemDao.itemsForList(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(itemList: ListItem) {
        listItemDao.insertItem(itemList)
    }

    suspend fun updateName(itemId: Int, parentId: Long, name: String) = listItemDao.updateName(itemId, parentId, name)

    suspend fun updatePrice(itemId: Int, parentId: Long, price: Double) = listItemDao.updatePrice(itemId, parentId, price)

    suspend fun updateQuantity(itemId: Int, parentId: Long, quantity: Int) = listItemDao.updateQuantity(itemId, parentId, quantity)

    suspend fun updateChecked(itemId: Int, parentId: Long, checked: Boolean) = listItemDao.updateChecked(itemId, parentId, checked)

    suspend fun update(item: ListItem) = listItemDao.update(item)

//    fun getListItems(key: Long) {
//        listItemDao.getItems(key)
//    }
}