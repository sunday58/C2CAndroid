package com.app.c2candroid.application.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.c2candroid.databinding.ImagesListItemsBinding
import com.bumptech.glide.Glide


class ExhibitImageAdapter (private  val listItem: List<String>):
    RecyclerView.Adapter<ExhibitImageAdapter.WeatherViewModel>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewModel {
        val binding = ImagesListItemsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewModel(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewModel, position: Int) {
        with(holder){
            with(listItem[position]){
                Glide.with(itemView)
                    .load(listItem)
                    .into(binding.exhibitImage)
            }
        }
    }

    override fun getItemCount() = listItem.size

    class WeatherViewModel(val binding: ImagesListItemsBinding):
        ViewHolder(binding.root)

}