package com.example.fetchhiring.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchhiring.FetchItemAdapter
import com.example.fetchhiring.Model.Fetch
import com.example.fetchhiring.R

class MainAdapter(val context : Context,
                  val listID : List<Int>,
                  private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(listID: Int)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewMainListId = itemView.findViewById<TextView>(R.id.textViewMainListID)

        fun bind(item: Int) {
            textViewMainListId.text = item.toString()
            itemView.setOnClickListener {
                itemClickListener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(listID[position])
    }

    override fun getItemCount(): Int {
        return listID.size
    }


}