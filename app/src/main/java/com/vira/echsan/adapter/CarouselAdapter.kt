package com.vira.echsan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.viewholder.CaraouselViewHolder

class CarouselAdapter (val posts: ArrayList<String>):RecyclerView.Adapter<CaraouselViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaraouselViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_carousel, parent, false)
        return CaraouselViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: CaraouselViewHolder, position: Int) {
        holder.text.text = posts[position]
    }

}