package edu.umsl.adam_harris.project3.ui.shoppingListScreen


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
import edu.umsl.adam_harris.project3.data.entities.ShoppingList

class NewListDialog : DialogFragment() {
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        //Empty function
//    }
    private lateinit var listViewModel: ListViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //here fragment_my_dialog is the UI of Custom Dialog
        listViewModel = ViewModelProvider(activity!!).get(ListViewModel::class.java)
        return inflater.inflate(R.layout.new_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton = view.findViewById<Button>(R.id.button_save)
        saveButton.setOnClickListener {
            val editText = view.findViewById<EditText>(R.id.edit_word)
            if (editText.text.isEmpty()) {
                Toast.makeText(
                    context,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
                dismiss()
            }
            else {
                val word = ShoppingList(0, editText.text.toString())
                listViewModel.insert(word)
                dismiss()
            }
        }
    }
}