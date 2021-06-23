package com.dmitron.awesomeweather.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitron.awesomeweather.databinding.ViewSearchItemBinding
import com.dmitron.awesomeweather.utils.executeAfter
import com.dmitron.awesomeweather.utils.layoutInflater

class SearchAdapter(
    private val itemClickListener: (SearchItem) -> Unit
) : ListAdapter<SearchItem, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {
    
    inner class SearchViewHolder(private val binding: ViewSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem) {
            binding.executeAfter {
                root.setOnClickListener { itemClickListener(item) }
                name = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(
            ViewSearchItemBinding.inflate(parent.context.layoutInflater(), parent, false)
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.cityId == newItem.cityId
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}