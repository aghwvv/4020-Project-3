package edu.umsl.adam_harris.project3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.umsl.adam_harris.project3.data.dao.ListItemDao
import edu.umsl.adam_harris.project3.data.dao.ShoppingDao
import edu.umsl.adam_harris.project3.data.entities.ListItem
import edu.umsl.adam_harris.project3.data.entities.ShoppingList

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [ShoppingList::class, ListItem::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
    abstract fun listItemDao(): ListItemDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getDatabase(
            context: Context
        ): ShoppingListDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListDatabase::class.java,
                    "shopping_list_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

//        private class ShoppingListDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            /**
//             * Override the onOpen method to populate the database.
//             * For this sample, we clear the database every time it is created or opened.
//             */
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//                // If you want to keep the data through app restarts,
//                // comment out the following line.
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
////                        populateDatabase(database.shoppingDao(), database.listItemDao())
//                    }
//                }
//            }
//        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
//        fun populateDatabase(shoppingDao: ShoppingDao, listItemDao: ListItemDao) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate on creation.
//            shoppingDao.deleteAll()
//            listItemDao.deleteAll()
//
//            var word = ShoppingList(0,"Hello")
//            shoppingDao.insertList(word)
//            word = ShoppingList(0,"World!")
//            shoppingDao.insertList(word)
//
//            var primaryKey = shoppingDao.getElementId()
//            var item = ListItem(primaryKey, "Banana", 3.14)
//            listItemDao.insertItem(item)
//
////            var item = ListItem(0, 0,"Apple", 3.14)
////            listItemDao.insertItem(item)
////            item = ListItem(0, 0, "Banana", 1.09)
////            listItemDao.insertItem(item)
//        }
    }

}