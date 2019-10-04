package com.vira.echsan.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.model.ResUmroh
import com.vira.echsan.ui.activity.auth.LoginActivity
import com.vira.echsan.ui.activity.hajiumroh.PaketUmrohDetailActivity
import kotlinx.android.synthetic.main.segment_paket.view.*

class PaketUmrohAdapter(private val context: Context) :
    RecyclerView.Adapter<PaketUmrohAdapter.ViewHolder>() {
    private var dataList: List<ResUmroh> = listOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position, context)
    }

    fun updateData(dataList: List<ResUmroh>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.segment_paket, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: ResUmroh, position: Int, ctx: Context) {
            val session = SessionManager(ctx)
            itemView.txtPoint.text = model.point + " POIN"
            itemView.txtHarga.text = "Rp. " + model.harga
            itemView.txtNamaPaket.text = model.namaPaket

            Glide.with(ctx).load(model.icon).into(itemView.imgIcon)

            itemView.containerBeli.setOnClickListener {
                if (session.getString(session.TOKEN).equals("", true)) {
                    Toast.makeText(ctx, "Login Dahulu!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(ctx, LoginActivity::class.java)
                    ctx.startActivity(intent)
                } else {
                    val intent = Intent(ctx, PaketUmrohDetailActivity::class.java)
                    intent.putExtra("id_paket", model.id)
                    ctx.startActivity(intent)
                }
            }
        }
    }
}
