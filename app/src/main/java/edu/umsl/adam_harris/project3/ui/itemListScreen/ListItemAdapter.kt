package edu.umsl.adam_harris.project3.ui.itemListScreen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import edu.umsl.adam_harris.project3.R
import edu.umsl.adam_harris.project3.data.entities.ListItem

class ListItemAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<ListItem>()
    private val itemClickHandler: ItemClickEventHandler = context as ItemClickEventHandler

    inner class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        val itemNameView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val priceItemView: TextView = itemView.findViewById(R.id.price)
        val quantityItemView: TextView = itemView.findViewById(R.id.itemQuantityText)
        val itemMenu: ImageButton = itemView.findViewById(R.id.itemMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val itemView = inflater.inflate(R.layout.item_layout, parent, false)

        return ListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val current = items[position]
        holder.checkBox.isChecked = current.isChecked
        holder.priceItemView.text = current.price.toString()
        holder.itemNameView.text = current.itemName
        holder.quantityItemView.text = current.quantity.toString()

        holder.checkBox.setOnCheckedChangeListener {
                _, isChecked ->
            if (isChecked) {
                itemClickHandler.forwardItemClick(position, true)
            } else {
                itemClickHandler.forwardItemClick(position, false)
            }
        }

        holder.itemMenu.setOnClickListener {
            //creating a popup menu
            val popup = PopupMenu(context, it)
            //inflating menu from xml resource
            popup.inflate(R.menu.drop_down_menu)
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.edit -> {
                        Log.e("edit", "Edit option clicked")
                        itemClickHandler.forwardEditClick(position)
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
    }

    internal fun setItems(itemLists: List<ListItem>) {
        this.items = itemLists
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    interface ItemClickEventHandler {
        fun forwardItemClick(position: Int, isChecked: Boolean)

        fun forwardEditClick(position: Int)
    }
}