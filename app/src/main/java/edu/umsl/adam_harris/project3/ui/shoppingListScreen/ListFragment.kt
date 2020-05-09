package edu.umsl.adam_harris.project3.ui.shoppingListScreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.umsl.adam_harris.project3.R
import kotlinx.android.synthetic.main.shopping_list_fragment.view.*

class ListFragment : Fragment() {
    private lateinit var listViewModel: ListViewModel
    private var shoppingListAdapter: ShoppingListAdapter? = null

    companion object {
        private const val DIALOG_LIST = "DialogList"
        private const val REQUEST_LIST = 0
    }

//    var mainActivityListener: MainActivityListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shopping_list_fragment, container, false)

        val fragmentActivity = activity

        val recyclerView = view.shoppingListRecyclerView
        val adapter = fragmentActivity?.let {
            ShoppingListAdapter(
                it
            )
        }
        if (adapter != null) {
            shoppingListAdapter = adapter
        }
        recyclerView.adapter = shoppingListAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
        recyclerView.addItemDecoration(DividerItemDecoration(fragmentActivity, LinearLayoutManager.VERTICAL))

//        // Get a new or existing ViewModel from the ViewModelProvider.
//        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
//
//        // Add an observer on the LiveData returned by getAlphabetizedWords.
//        // The onChanged() method fires when the observed data changes and the activity is
//        // in the foreground.
//        listViewModel.allWords.observe(viewLifecycleOwner, Observer { words ->
//            // Update the cached copy of the words in the adapter.
//            words?.let {
//                adapter?.setWords(it)
//            }
//        })

//        view.newListButton.setOnClickListener(newListButtonPressed)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Get a new or existing ViewModel from the ViewModelProvider.
        activity?.let {
            listViewModel = ViewModelProvider(it).get(ListViewModel::class.java)

            // Add an observer on the LiveData returned by getAlphabetizedWords.
            // The onChanged() method fires when the observed data changes and the activity is
            // in the foreground.
            listViewModel.allLists.observe(viewLifecycleOwner, Observer { lists ->
                // Update the cached copy of the words in the adapter.
                lists?.let { shoppingList ->
                    shoppingListAdapter?.setWords(shoppingList)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.new_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addListMenuOption -> {
                newListButtonPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun newListButtonPressed() {
        val dialog = NewListDialog()
        dialog.setTargetFragment(this, REQUEST_LIST)
        dialog.show(parentFragmentManager, DIALOG_LIST)
    }
}