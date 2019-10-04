package com.vira.echsan.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import kotlinx.android.synthetic.main.segment_image.view.*

class ImageAdapter(private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var dataList: List<Int> = listOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position, context)
    }

    fun updateData(dataList: List<Int>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.segment_image, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Int, position: Int, ctx: Context) {
            val session = SessionManager(ctx)

            Glide.with(ctx).load(model).into(itemView.imgPhoto)

        }
    }
}
