package edu.umsl.adam_harris.project3.ui.itemListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import edu.umsl.adam_harris.project3.R
import edu.umsl.adam_harris.project3.data.entities.ListItem

class NewItemDialog : DialogFragment() {
    private lateinit var itemViewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemViewModel = ViewModelProvider(activity!!).get(ItemViewModel::class.java)
        return inflater.inflate(R.layout.new_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val itemNameText = view.findViewById<EditText>(R.id.itemNameEditText)
            val priceText = view.findViewById<EditText>(R.id.itemPriceEditText)
            val quantityText = view.findViewById<EditText>(R.id.itemQuantity)
            if (itemNameText.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "Item not saved because its name is empty.",
                    Toast.LENGTH_LONG
                ).show()
                dismiss()
            }
            else {
                val item = ListItem(itemViewModel.listId, itemNameText.text.toString(), priceText.text.toString().toDouble(), quantityText.text.toString().toInt())
                itemViewModel.insert(item)
                dismiss()
            }
        }
    }
}