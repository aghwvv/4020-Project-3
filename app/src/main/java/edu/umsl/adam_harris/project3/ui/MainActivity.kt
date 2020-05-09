package edu.umsl.adam_harris.project3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.umsl.adam_harris.project3.R
import edu.umsl.adam_harris.project3.data.entities.ListItem
import edu.umsl.adam_harris.project3.ui.itemListScreen.EditItemDialog
import edu.umsl.adam_harris.project3.ui.itemListScreen.ItemFragment
import edu.umsl.adam_harris.project3.ui.itemListScreen.ItemViewModel
import edu.umsl.adam_harris.project3.ui.itemListScreen.ListItemAdapter
import edu.umsl.adam_harris.project3.ui.shoppingListScreen.ListFragment
import edu.umsl.adam_harris.project3.ui.shoppingListScreen.ListViewModel
import edu.umsl.adam_harris.project3.ui.shoppingListScreen.ShoppingListAdapter

class MainActivity : AppCompatActivity(), ShoppingListAdapter.ClickEventHandler, ListItemAdapter.ItemClickEventHandler {

    private lateinit var listViewModel: ListViewModel
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var listViewFragment: ListFragment
    private lateinit var itemViewFragment: ItemFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        listViewFragment = ListFragment()

        if (savedInstanceState != null) {
//            val fragment = supportFragmentManager.getFragment(savedInstanceState, "currentState")
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment!!)
//                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, listViewFragment)
                .commit()
        }

//        val recyclerView = findViewById<RecyclerView>(R.id.shoppingListRecyclerView)
//        val adapter = ShoppingListAdapter(this)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Get a new or existing ViewModel from the ViewModelProvider.
//        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
//
//        // Add an observer on the LiveData returned by getAlphabetizedWords.
//        // The onChanged() method fires when the observed data changes and the activity is
//        // in the foreground.
//        listViewModel.allWords.observe(this, Observer { words ->
//            // Update the cached copy of the words in the adapter.
//            words?.let { adapter.setWords(it) }
//        })

//        val fab = findViewById<FloatingActionButton>(R.id.fab)
//        fab.setOnClickListener {
////            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
////            startActivityForResult(intent, newWordActivityRequestCode)
//            val fragmentTransaction = supportFragmentManager.beginTransaction()
//            val prev = supportFragmentManager.findFragmentByTag("dialog")
//            if (prev != null) {
//                fragmentTransaction.remove(prev)
//            }
//            fragmentTransaction.addToBackStack(null)
//            val dialogFragment =
//                NewListDialog() //here MyDialog is my custom dialog
//            dialogFragment.show(fragmentTransaction, "dialog")
//        }

//        listViewFragment.mainActivityListener = object: ListFragment.MainActivityListener {
//            override fun newList() {
//                val fragmentTransaction = supportFragmentManager.beginTransaction()
//                val prev = supportFragmentManager.findFragmentByTag("dialog")
//                if (prev != null) {
//                    fragmentTransaction.remove(prev)
//                }
//                fragmentTransaction.addToBackStack(null)
//                val dialogFragment =
//                    NewListDialog() //here MyDialog is my custom dialog
//                dialogFragment.show(fragmentTransaction, "dialog")
//            }
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//
//        val fragment = supportFragmentManager.findFragmentByTag("list")
//        if (fragment != null) {
//            supportFragmentManager.putFragment(outState, "currentState", fragment)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            intentData?.let { data ->
//                val word = ShoppingList(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
//                listViewModel.insert(word)
//                Unit
//            }
//        } else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    override fun forwardClick(position: Int) {

        itemViewFragment = ItemFragment()

        itemViewFragment.arguments = Bundle().apply {
            val list = listViewModel.allLists.value?.get(position)
            list?.let { shopList ->
                this.putLong("listId", shopList.id)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, itemViewFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun forwardItemClick(position: Int, isChecked: Boolean) {
        val item = itemViewModel.allItems.value?.get(position)
//        when (item?.isChecked) {
//            true -> itemViewModel.updateChecked(item.itemId, item.parentListId, false)
//            false -> itemViewModel.updateChecked(item.itemId, item.parentListId, true)
//        }
        itemViewModel.updateChecked(item!!.itemId, item.parentListId, isChecked)
    }

    override fun forwardEditClick(position: Int) {
        val item = itemViewModel.allItems.value?.get(position)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment = EditItemDialog(item!!) //here MyDialog is my custom dialog
        dialogFragment.show(fragmentTransaction, "dialog")
    }
}