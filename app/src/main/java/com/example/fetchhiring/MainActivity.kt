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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.ViewModel.DetailActivity
import com.example.fetchhiring.ViewModel.MainAdapter
import com.example.fetchhiring.ViewModel.MainViewModel
import com.example.fetchhiring.ViewModel.MyViewModelFactory
import com.example.fetchhiring.databinding.ActivityMainBinding

private lateinit var mainActivityBinding : ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private val listIDs = mutableListOf<Int>()
    private val items = mutableListOf<Fetch>()
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
        viewModel = ViewModelProvider(this,MyViewModelFactory(this)).get(MainViewModel::class.java)
        viewModel.getData()

        viewModel.listIds.observe(this, Observer { newListIds ->
            Log.i("MainActivity","listIDs = ${newListIds}")
            listIDs.clear()
            listIDs.addAll(newListIds)
            mainAdapter.notifyDataSetChanged()
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
                mainActivityBinding.textViewError.text =
                    getString(R.string.error_while_loading_results_please_check_your_internet_connection)
                Toast.makeText(this,
                    getString(R.string.error_while_loading_results), Toast.LENGTH_SHORT).show()
            }
        })
//        Log.i("MainActivity","items = $items")
        mainAdapter = MainAdapter(this, listIDs, object : MainAdapter.ItemClickListener{
            override fun onItemClick(listID: Int) {
                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra(getString(R.string.fetch_id),listID)
                startActivity(intent)
            }

        })
        mainActivityBinding.mainRecyclerView.adapter = mainAdapter
        mainActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
    }

}