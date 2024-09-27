package com.example.fetchhiring.ViewModel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.R
import com.example.fetchhiring.databinding.ActivityDetailBinding
import com.example.fetchhiring.databinding.ActivityMainBinding

private lateinit var detailActivityBinding : ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var  items  : Array<Fetch>

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
        val id = intent.getIntExtra("FETCH_ID",0)
        Toast.makeText(this,"$id",Toast.LENGTH_LONG).show()
    }
}