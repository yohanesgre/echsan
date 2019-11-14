package com.vira.echsan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import kotlinx.android.synthetic.main.item_carousel_promo.view.*

class CarouselPromoAdapter (val posts: ArrayList<String>):RecyclerView.Adapter<CarouselPromoAdapter.CaraouselPromoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaraouselPromoViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carousel_promo, parent, false)
        return CaraouselPromoViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: CaraouselPromoViewHolder, position: Int) {
        holder.text.text = posts[position]
    }

    class CaraouselPromoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.cv_text_test
    }
}