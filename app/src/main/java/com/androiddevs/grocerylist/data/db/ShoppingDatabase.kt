package com.androiddevs.grocerylist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

//    companion object{
//        @Volatile
//        private var INSTANCE: ShoppingDatabase?=null
//
//        fun getDatabase(context: Context):ShoppingDatabase{
//
//            val tempInstance = INSTANCE
//            if(tempInstance!=null){
//                return tempInstance
//            }
//            synchronized(this){
//
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ShoppingDatabase::class.java,
//                    "shopping_items"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()


        //Make userdatabase a singleton class
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: createDatabase(
                    context
                ).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}