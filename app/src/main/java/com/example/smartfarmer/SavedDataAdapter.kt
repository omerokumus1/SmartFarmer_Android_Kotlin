package com.example.smartfarmer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfarmer.databinding.WeatherDataItemBinding
import com.example.smartfarmer.db.WeatherData

class SavedDataAdapter : RecyclerView.Adapter<SavedDataAdapter.SavedDataViewHolder>() {

    var list = listOf<WeatherData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherDataItemBinding.inflate(layoutInflater, parent, false)
        return SavedDataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SavedDataViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            topraginNemi.text = "Toprağın Nemi: ${item.topraginNemi}"
            topraginSicakligi.text = "Toprağın Sıcaklığı: ${item.topraginSicakligi}"
            havaninNemi.text = "Havanın Nemi: ${item.havaninNemi}"
            havaninSicakligi.text = "Havanın Sıcaklığı: ${item.havaninSicakligi}"
            ruzgarDurumuYonu.text = "Rüzgar Durumu Yönü: ${item.ruzgarDurumuYonu}"
            ruzgarHizi.text = "Rüzgar Hızı: ${item.ruzgarHizi}"
            tarih.text = "Tarih: ${item.date}"
        }
    }


    class SavedDataViewHolder(val binding: WeatherDataItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}