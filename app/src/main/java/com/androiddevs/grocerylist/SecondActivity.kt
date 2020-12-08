package com.androiddevs.grocerylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository
import com.androiddevs.grocerylist.ui.shoppinglist.ShoppingActivity
import com.androiddevs.grocerylist.ui.shoppinglist.ShoppingViewModel
import com.androiddevs.grocerylist.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var viewModel: ShoppingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)
        //Code for switching between 2 fragments
        val updateFragment = UpdateFragment()
        val secondFragment = SecondFragment()

        //Replace content of default layout
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, updateFragment)
            commit()
        }


        //Go to activity 1
        activity1.setOnClickListener{

            val intent = Intent(this, ShoppingActivity::class.java)
            startActivity(intent)
        }

//        btnUpdateFragment

        btnFragment2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.flFragment, secondFragment)
                commit()
            }
        }


    }

}