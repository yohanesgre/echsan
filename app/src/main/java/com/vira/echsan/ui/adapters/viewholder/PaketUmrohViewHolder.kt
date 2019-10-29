package com.vira.echsan.ui.adapters.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cardview_umroh_paket.view.*

class PaketUmrohViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val paket: TextView = itemView.tv_umroh_paket_cv_paket
    val tanggal: TextView = itemView.tv_umroh_paket_cv_berangkat
    val durasi: TextView = itemView.tv_umroh_paket_cv_durasi
    val hotel: TextView = itemView.tv_umroh_paket_cv_hotel
    val penerbangan: TextView = itemView.tv_umroh_paket_cv_penerbangan
    val travel: TextView = itemView.tv_umroh_paket_cv_travel
    val harga: TextView = itemView.tv_umroh_paket_cv_harga2
}
