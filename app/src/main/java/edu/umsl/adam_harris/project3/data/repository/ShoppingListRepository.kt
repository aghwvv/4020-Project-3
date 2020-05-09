package edu.umsl.adam_harris.project3.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import edu.umsl.adam_harris.project3.data.dao.ShoppingDao
import edu.umsl.adam_harris.project3.data.entities.ShoppingList

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class ShoppingListRepository(private val shoppingDao: ShoppingDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<ShoppingList>> = shoppingDao.getLists()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(shoppingList: ShoppingList) {
        shoppingDao.insertList(shoppingList)
    }
}