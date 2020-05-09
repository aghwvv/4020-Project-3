package edu.umsl.adam_harris.project3.ui.shoppingListScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.umsl.adam_harris.project3.data.database.ShoppingListDatabase
import edu.umsl.adam_harris.project3.data.entities.ShoppingList
import edu.umsl.adam_harris.project3.data.repository.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShoppingListRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allLists: LiveData<List<ShoppingList>>

    init {
        val wordsDao = ShoppingListDatabase.getDatabase(application).shoppingDao()
        repository = ShoppingListRepository(wordsDao)
        allLists = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(shoppingList: ShoppingList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(shoppingList)
    }
}