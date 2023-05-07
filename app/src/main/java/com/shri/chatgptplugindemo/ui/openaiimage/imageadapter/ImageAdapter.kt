package com.shri.chatgptplugindemo.ui.openaiimage.imageadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.image.ImageURL
import com.bumptech.glide.Glide
import com.shri.chatgptdemo.R


class ImageAdapter @OptIn(BetaOpenAI::class) constructor(var items: List<ImageURL>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout
            , parent, false)
        return ViewHolder(view)
    }

    @OptIn(BetaOpenAI::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.image.context)
            .load(items.get(position).url)
            .into(holder.image)

    }

    @OptIn(BetaOpenAI::class)
    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(BetaOpenAI::class)
    fun setImageUrlList(imageURL: List<ImageURL>) {
        items = imageURL
        notifyDataSetChanged()
    }
}