package com.exercise.retrofitexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.retrofitexercise.databinding.ItemFilmBinding
import com.exercise.retrofitexercise.databinding.ItemNewsBinding
import com.exercise.retrofitexercise.model.ResponseDataFilmItem
import com.exercise.retrofitexercise.model.ResponseDataNewsItem

class FilmAdapter(var listFilm: List<ResponseDataFilmItem>): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleFilm.text = listFilm[position].name
        holder.binding.dateFilm.text = listFilm[position].date
        holder.binding.directorFilm.text = listFilm[position].director
        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.imgFilm)
    }
}