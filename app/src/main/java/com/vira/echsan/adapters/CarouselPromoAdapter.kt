package com.vira.echsan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.adapters.viewholder.CaraouselPromoViewHolder

class CarouselPromoAdapter (val posts: ArrayList<String>):RecyclerView.Adapter<CaraouselPromoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaraouselPromoViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_carousel_promo, parent, false)
        return CaraouselPromoViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: CaraouselPromoViewHolder, position: Int) {
        holder.text.text = posts[position]
    }

}