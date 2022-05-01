package com.app.c2candroid.application.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.c2candroid.databinding.ExhibitListItemsBinding
import com.app.c2candroid.model.Exhibit


class ExhibitAdapter (private  val listItem: List<Exhibit>):
    RecyclerView.Adapter<ExhibitAdapter.WeatherViewModel>(){

    private lateinit var adapter: ExhibitImageAdapter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewModel {
        val binding = ExhibitListItemsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewModel(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewModel, position: Int) {
        with(holder){
            with(listItem[position]){
                binding.title.text = title

                binding.imagesRecycler.layoutManager =
                    LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
                adapter = ExhibitImageAdapter(images)
                binding.imagesRecycler.adapter = adapter

            }
        }
    }

    override fun getItemCount() = listItem.size

    class WeatherViewModel(val binding: ExhibitListItemsBinding):
        ViewHolder(binding.root)

}