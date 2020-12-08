package com.androiddevs.grocerylist.ui.shoppinglist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    //This can also start in the main thread
    fun insert(item: ShoppingItem) =
        GlobalScope.launch(Dispatchers.IO) {
            repository.insert(item)

            Log.i("look", "name of this thread is ${Thread.currentThread().name}")
        }

    fun update(name: String, id: Int) =
        GlobalScope.launch(Dispatchers.Default) {
            repository.update(name, id)
            Log.i("look", "name of this thread is ${Thread.currentThread().name}")
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}
