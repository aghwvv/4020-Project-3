package edu.umsl.adam_harris.project3.ui.shoppingListScreen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import edu.umsl.adam_harris.project3.R
import edu.umsl.adam_harris.project3.data.entities.ShoppingList


class ShoppingListAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<ShoppingListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<ShoppingList>() // Cached copy of words
    private val clickHandler: ClickEventHandler = context as ClickEventHandler

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.listName)
        val dropDownMenuButton: ImageButton = itemView.findViewById(R.id.listMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.shopping_list_entry_layout, parent, false)

        return WordViewHolder(itemView).also { holder ->

            itemView.setOnClickListener {
                clickHandler.forwardClick(holder.adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.listName
        holder.dropDownMenuButton.setOnClickListener {
            //creating a popup menu
            val popup = PopupMenu(context, it)
            //inflating menu from xml resource
            popup.inflate(R.menu.drop_down_menu)
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.edit -> {
                        Log.e("edit", "Edit option clicked")
                        true
                    }
                    R.id.delete -> {
                        Log.e("delete", "Delete option clicked")
                        true
                    }
                    else -> false
                }
            }
            //displaying the popup
            popup.show()
        }
//        holder.wordItemView.setOnClickListener {
//            Log.e("ShoppingListAdapter", "onClickListener Triggered")
//        }
    }

    internal fun setWords(shoppingLists: List<ShoppingList>) {
        this.words = shoppingLists
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size

    interface ClickEventHandler {
        fun forwardClick(position: Int)
    }
}