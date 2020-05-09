package edu.umsl.adam_harris.project3.ui.itemListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import edu.umsl.adam_harris.project3.R
import edu.umsl.adam_harris.project3.data.entities.ListItem

class EditItemDialog internal constructor(
    private val item: ListItem
): DialogFragment() {
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

        val titleTextView = view.findViewById<TextView>(R.id.addItemTitle)
        val itemNameText = view.findViewById<EditText>(R.id.itemNameEditText)
        val priceText = view.findViewById<EditText>(R.id.itemPriceEditText)
        val quantityText = view.findViewById<EditText>(R.id.itemQuantity)
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        titleTextView.text = String.format("Edit Item")
        itemNameText.setText(item.itemName)
        priceText.setText(item.price.toString())
        quantityText.setText(item.quantity.toString())

        saveButton.setOnClickListener {
            if (itemNameText.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "Item not updated because the name was empty.",
                    Toast.LENGTH_LONG
                ).show()
                dismiss()
            }
            else {
                item.itemName = itemNameText.text.toString()
                item.price = priceText.text.toString().toDouble()
                item.quantity = quantityText.text.toString().toInt()

                itemViewModel.update(item)
                dismiss()
            }
        }
    }
}