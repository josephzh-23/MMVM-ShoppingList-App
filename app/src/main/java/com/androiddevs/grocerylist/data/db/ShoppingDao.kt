package com.androiddevs.grocerylist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {


    //Upsert allows user to update and insert a row in sql
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("Update shopping_items SET item_name = :name WHERE ID = :id")
    suspend fun update(name: String, id: Int);
}











