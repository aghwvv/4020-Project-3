package edu.umsl.adam_harris.project3.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "list_table")
data class ShoppingList(@ColumnInfo(name = "id")
                        @PrimaryKey(autoGenerate = true)
                        val id: Long,
                        @ColumnInfo(name = "name") val listName: String)