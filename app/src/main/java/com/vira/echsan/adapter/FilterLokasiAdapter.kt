package com.vira.echsan.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.model.recyclerview.FilterDurasiModel
import com.vira.echsan.model.recyclerview.FilterLokasiModel
import kotlinx.android.synthetic.main.item_filter_durasi.view.*

open class FilterLokasiAdapter(val context: Context, val listLokasi: ArrayList<FilterLokasiModel>) : RecyclerView.Adapter<FilterLokasiAdapter.SingleViewHolder>(){

    protected var checkedPosition:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_filter_lokasi, parent,false)
        return SingleViewHolder(view)
    }

    override fun getItemCount(): Int = listLokasi.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        holder.bind(listLokasi[position])
    }

    enum class StrokeColor{
        Primary,
        Black
    }

    inner class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun changeStrokeColor(view: View, color:StrokeColor) {
            val drawable = view.background as? GradientDrawable
            if (color == StrokeColor.Primary){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    drawable?.setStroke(1, context.getColor(R.color.colorPrimary))
                }
            }else if (color == StrokeColor.Black){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    drawable?.setStroke(1, context.getColor(R.color.black))
                }
            }
        }

        fun bind(lokasiModel: FilterLokasiModel){
            if (checkedPosition == -1){
                changeStrokeColor(itemView, StrokeColor.Black)
            }else{
                if (checkedPosition == adapterPosition){
                    changeStrokeColor(itemView, StrokeColor.Primary)
                }else{
                    changeStrokeColor(itemView, StrokeColor.Black)
                }
            }

            itemView.item_tv.text = lokasiModel.lokasi

            itemView.setOnClickListener{
                changeStrokeColor(it, StrokeColor.Primary)
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }
            }
        }
    }
}