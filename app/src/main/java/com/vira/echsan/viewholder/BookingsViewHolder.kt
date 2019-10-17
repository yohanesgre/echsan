package com.vira.echsan.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.vira.echsan.R
import com.vira.echsan.model.recyclerview.BookingsModel

class BookingsViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(inflater.inflate(R.layout.cardview_bookings, parent, false)){
    private var catTravel: TextView? = null
    private var date: TextView? = null
    private var travel: TextView? = null
    private var duration: TextView? = null

    init {
        catTravel = itemView.findViewById(R.id.booking_content_header_title)
        date = itemView.findViewById(R.id.booking_content_header_date)
        travel = itemView.findViewById(R.id.booking_content_body_travel)
        duration = itemView.findViewById(R.id.booking_content_body_duration)
    }

    fun bind(model: BookingsModel){
        catTravel?.text = if(model.cat == 1) "Umroh" else "Haji"
        date?.text = model.date
        travel?.text = model.travel
        duration?.text = model.duration
    }
}

