package com.exercise.retrofitexercise.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.retrofitexercise.DetailNewsActivity
import com.exercise.retrofitexercise.UpdateNewsActivity
import com.exercise.retrofitexercise.databinding.ItemNewsBinding
import com.exercise.retrofitexercise.model.ResponseDataNewsItem

class NewsAdapter(var listNews: List<ResponseDataNewsItem>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleNews.text = listNews[position].title
        holder.binding.dateNews.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.binding.imgNews)
        holder.binding.btnUpdate.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("id", listNews[position].id.toInt())
            }
            val intent = Intent(holder.itemView.context, UpdateNewsActivity::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.cvItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            intent.putExtra("id", listNews[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}