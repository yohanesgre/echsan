package com.vira.echsan.adapters.umroh

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.R
import com.vira.echsan.databinding.ItemUmrohPaketPilihTanggalBinding
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel


class PaketTanggalAdapter(
    private val context: Context,
    private val sharedViewModel: UmrohSharedViewModel
) : ListAdapter<Int, PaketTanggalAdapter.ViewHolder>(PaketTanggalDiffCallback()) {

    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUmrohPaketPilihTanggalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    enum class StrokeColor{
        Primary,
        Black
    }

    inner class ViewHolder(
        private val binding:ItemUmrohPaketPilihTanggalBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun changeStrokeColor(view: View, color: StrokeColor) {
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

        fun bind(_tanggal:Int) {
            if (checkedPosition == -1){
                changeStrokeColor(itemView,
                    StrokeColor.Black
                )
            }else{
                if (checkedPosition == adapterPosition){
                    changeStrokeColor(itemView,
                        StrokeColor.Primary
                    )
                }else{
                    changeStrokeColor(itemView,
                        StrokeColor.Black
                    )
                }
            }
            binding.apply{
                tanggal = _tanggal
                clickListener = View.OnClickListener {
                    changeStrokeColor(it,
                        StrokeColor.Primary
                    )
                    if (checkedPosition != adapterPosition) {
                        notifyItemChanged(checkedPosition)
                        checkedPosition = adapterPosition
                        //sharedViewModel.setSelectedTanggal(_tanggal)
                    }
                }
                executePendingBindings()
            }
        }
    }
}

private class PaketTanggalDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}