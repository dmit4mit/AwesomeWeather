package com.dmitron.bottlerocketweather.city.hourlyadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitron.bottlerocketweather.databinding.ViewWeatherHourlyBinding
import com.dmitron.bottlerocketweather.utils.executeAfter
import com.dmitron.bottlerocketweather.utils.layoutInflater

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {
    private var items: List<HourlyWeatherViewData> = listOf()

    class HourlyWeatherViewHolder(private val binding: ViewWeatherHourlyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HourlyWeatherViewData) {
            binding.executeAfter {
                data = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HourlyWeatherViewHolder(
            ViewWeatherHourlyBinding.inflate(parent.context.layoutInflater(), parent, false)
        )

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(data: List<HourlyWeatherViewData>) {
        this.items = data
        notifyDataSetChanged()
    }
}