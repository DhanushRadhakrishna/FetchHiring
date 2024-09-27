package com.example.fetchhiring

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchhiring.Model.Fetch

class FetchItemAdapter(private val context : Context, private val items : List<Fetch>) : RecyclerView.Adapter<FetchItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewId = itemView.findViewById<TextView>(R.id.textViewId)
        private val textViewListId = itemView.findViewById<TextView>(R.id.textViewListId)
        private val textViewName = itemView.findViewById<TextView>(R.id.textViewName)

        fun bind(item: Fetch) {
            textViewId.text = item.id.toString()
            textViewListId.text = item.listId.toString()
            textViewName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchItemAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FetchItemAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}