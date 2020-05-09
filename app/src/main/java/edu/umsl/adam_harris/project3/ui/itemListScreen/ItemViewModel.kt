package edu.umsl.adam_harris.project3.ui.itemListScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.umsl.adam_harris.project3.data.database.ShoppingListDatabase
import edu.umsl.adam_harris.project3.data.entities.ListItem
import edu.umsl.adam_harris.project3.data.repository.ListItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ListItemRepository

    lateinit var allItems: LiveData<List<ListItem>>
    private set

    var listId: Long = 0
    private set

    init {
        val listItemDao = ShoppingListDatabase.getDatabase(application).listItemDao()
        repository = ListItemRepository(listItemDao)
//        allItems = repository.allItems
    }

    fun fetchListForId(id: Long) {
        listId = id
        allItems = repository.itemsForList(id)
    }

    fun insert(itemList: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(itemList)
    }

    fun updateChecked(itemId: Int, parentId: Long, checked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateChecked(itemId, parentId, checked)
    }

    fun update(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }
}