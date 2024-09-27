package com.example.fetchhiring.ViewModel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchhiring.FetchItemAdapter
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.R
import com.example.fetchhiring.databinding.ActivityDetailBinding
import com.example.fetchhiring.databinding.ActivityMainBinding

private lateinit var detailActivityBinding : ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailViewModel
    private val items = mutableListOf<Fetch>()
    private lateinit var fetchItemAdapter: FetchItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getIntExtra(getString(R.string.fetch_id),0)
        viewModel = ViewModelProvider(this,DetailViewModelFactory(id,this)).get(DetailViewModel::class.java)
        viewModel.getDataFromRepository()
        viewModel.items.observe(this, Observer { newItems ->
//            Log.i("DetailActivity","$newItems")
            items.clear()
            items.addAll(newItems)
            fetchItemAdapter.notifyDataSetChanged()
        })
        fetchItemAdapter = FetchItemAdapter(this,items)
        detailActivityBinding.mainRecyclerView.adapter = fetchItemAdapter
        detailActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}