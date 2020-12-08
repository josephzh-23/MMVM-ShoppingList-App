package com.androiddevs.grocerylist.data.repositories

import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.data.db.ShoppingDatabase

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun insert(item: ShoppingItem) = db.getShoppingDao().insert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    suspend fun update(name:String, id: Int) = db.getShoppingDao().update(name, id)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}