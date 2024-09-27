package com.example.fetchhiring

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.ViewModel.MainViewModel
import com.example.fetchhiring.databinding.ActivityMainBinding

private lateinit var mainActivityBinding : ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private val items = mutableListOf<Fetch>()
    private lateinit var fetchItemAdapter: FetchItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = MainViewModel()
        viewModel.getData()
        viewModel.items.observe(this, Observer { newItems ->
            Log.i("MainActivity","newItems = $newItems")
            items.clear()
            items.addAll(newItems)
            fetchItemAdapter.notifyDataSetChanged()
            mainActivityBinding.textViewError.visibility = View.GONE

        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
//            Log.i(TAG, "isLoading $isLoading")
            mainActivityBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            mainActivityBinding.textViewError.visibility = View.GONE
        })
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                mainActivityBinding.progressBar.visibility = View.GONE
                mainActivityBinding.textViewError.text = "Error while loading results. Please check your internet connection"
                Toast.makeText(this, "Error while loading results", Toast.LENGTH_SHORT).show()
            }
        })
//        Log.i("MainActivity","items = $items")
        fetchItemAdapter = FetchItemAdapter(this, items)
        mainActivityBinding.itemsRecyclerView.adapter = fetchItemAdapter
        mainActivityBinding.itemsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

}