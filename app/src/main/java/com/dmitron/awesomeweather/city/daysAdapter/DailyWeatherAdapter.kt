package com.dmitron.awesomeweather.city.daysAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitron.awesomeweather.databinding.ViewWeatherDailyBinding
import com.dmitron.awesomeweather.utils.executeAfter
import com.dmitron.awesomeweather.utils.layoutInflater

class DailyWeatherAdapter(
    private val dayClickListener: (Int) -> Unit,
) : ListAdapter<DailyWeatherViewData, DailyWeatherAdapter.DailyWeatherViewHolder>(DIFF_CALLBACK) {
    inner class DailyWeatherViewHolder(private val binding: ViewWeatherDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyWeatherViewData) {
            binding.executeAfter {
                root.setOnClickListener {
                    dayClickListener(item.dayOfTheWeekNumber)
                }
                data = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DailyWeatherViewHolder(
            ViewWeatherDailyBinding.inflate(parent.context.layoutInflater(), parent, false)
        )

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DailyWeatherViewData>() {
            override fun areItemsTheSame(
                oldItem: DailyWeatherViewData,
                newItem: DailyWeatherViewData,
            ): Boolean {
                return oldItem.dayOfTheWeekDisplay == newItem.dayOfTheWeekDisplay
            }

            override fun areContentsTheSame(
                oldItem: DailyWeatherViewData,
                newItem: DailyWeatherViewData,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}