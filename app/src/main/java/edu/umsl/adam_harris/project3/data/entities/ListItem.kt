package edu.umsl.adam_harris.project3.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "item_table",
        foreignKeys = [ForeignKey(entity = ShoppingList::class,
            parentColumns = ["id"],
            childColumns = ["parent_list_id"],
            onDelete = ForeignKey.CASCADE)]
)
data class ListItem(@ColumnInfo(name = "parent_list_id", index = true) val parentListId: Long,
                    @ColumnInfo(name = "item_name") var itemName: String,
                    @ColumnInfo(name = "price") var price: Double,
                    @ColumnInfo(name = "quantity") var quantity: Int) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0
    @ColumnInfo(name = "is_checked")
    var isChecked: Boolean = false
}