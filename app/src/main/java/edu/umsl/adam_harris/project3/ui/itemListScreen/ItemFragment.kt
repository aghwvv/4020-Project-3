package edu.umsl.adam_harris.project3.ui.itemListScreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.umsl.adam_harris.project3.R
import kotlinx.android.synthetic.main.item_fragment_layout.view.*

class ItemFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel
    private var listItemAdapter: ListItemAdapter? = null

    companion object {
        private const val DIALOG_ITEM = "DialogItem"
        private const val REQUEST_ITEM = 0
    }

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
        val view = inflater.inflate(R.layout.item_fragment_layout, container, false)

        val fragmentActivity = activity
        val recyclerView = view.itemListRecyclerView
        val adapter = fragmentActivity?.let {
            ListItemAdapter(
                it
            )
        }
        if (adapter != null) {
            listItemAdapter = adapter
        }
        recyclerView.adapter = listItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
        recyclerView.addItemDecoration(DividerItemDecoration(fragmentActivity, LinearLayoutManager.VERTICAL))

//        view.addItemButton.setOnClickListener(addItemButtonPressed) {
//            val item = ListItem(itemViewModel.listId, "Banana", 3.14)
//            itemViewModel.insert(item)
//        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemViewModel = ViewModelProvider(activity!!).get(ItemViewModel::class.java)
        arguments?.let {
            val id = it.getLong("listId")
            itemViewModel.fetchListForId(id)
        }

        itemViewModel.allItems.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                listItemAdapter?.setItems(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.new_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addItemMenuOption -> {
                addItemButtonPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addItemButtonPressed() {
        val dialog = NewItemDialog()
        dialog.setTargetFragment(this, REQUEST_ITEM)
        dialog.show(parentFragmentManager, DIALOG_ITEM)
    }

//    override fun forwardItemClick(position: Int, isChecked: Boolean) {
//        val item = itemViewModel.allItems.value?.get(position)
////        when (item?.isChecked) {
////            true -> itemViewModel.updateChecked(item.itemId, item.parentListId, false)
////            false -> itemViewModel.updateChecked(item.itemId, item.parentListId, true)
////        }
//        itemViewModel.updateChecked(item!!.itemId, item.parentListId, item.isChecked)
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        textView = view.findViewById(R.id.price)
//        textView.text = String.format("1st Fragment")
//    }
}