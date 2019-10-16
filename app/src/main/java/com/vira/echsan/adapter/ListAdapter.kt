package com.vira.echsan.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vira.echsan.model.recyclerview.BookingsModel
import com.vira.echsan.ui.viewholder.BookingsViewHolder

class ListAdapter(private val list: List<BookingsModel>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<BookingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookingsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val movie: BookingsModel = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}