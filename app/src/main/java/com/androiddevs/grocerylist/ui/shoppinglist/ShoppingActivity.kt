package com.androiddevs.grocerylist.ui.shoppinglist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.grocerylist.R
import com.androiddevs.grocerylist.SecondActivity
import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*

//class ShoppingActivity : AppCompatActivity(), KodeinAware {
class ShoppingActivity : AppCompatActivity() {

//    override val kodein by kodein()
//    private val factory: ShoppingViewModelFactory by instance()


    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        //Setting up the database and make it dependant on the ShoppingActivity
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter


        //Takes you to the second activity
        secondActivityBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        //This updates the RecyclerView any time any data changes
        // Observe called when sth different is returned
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })


        //Here call the AddDialogLisner
        fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.insert(item)
                    }
                }).show()
        }
    }

}
