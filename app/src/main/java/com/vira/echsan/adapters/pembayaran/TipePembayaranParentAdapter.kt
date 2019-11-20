package com.vira.echsan.adapters.pembayaran

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.vira.echsan.R
import kotlinx.android.synthetic.main.item_tipe_pembayaran_child.view.*
import kotlinx.android.synthetic.main.item_tipe_pembayaran_parent.view.*
import kotlinx.android.synthetic.main.item_tipe_pembayaran_section.view.*

class TipePembayaranParentAdapter : BaseAdapter(){

    init {
        addSection(ArrayList<SectionItem>())
    }

    fun addSectionItem(sectionItem: SectionItem) {
        addItemOnSection(0, sectionItem)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_tipe_pembayaran_section

    override fun viewHolder(layout: Int, view: View) = ViewHolder(view)

    class ViewHolder(view:View):BaseViewHolder(view), TipePembayaranChildAdapter.ViewHolder.Delegate{

        private lateinit var sectionItem: SectionItem
        private val childAdapter = TipePembayaranChildAdapter(this)

        override fun bindData(data: Any) {
            if(data is SectionItem){
                this.sectionItem = data
                drawItemUI()
            }
        }

        private fun drawItemUI() {
            itemView.run {
                with(expandableLayout) {
                    parentLayoutResource = R.layout.item_tipe_pembayaran_parent
                    secondLayoutResource = R.layout.item_tipe_pembayaran_child
                    duration = 200L
                    parentLayout.title.text = sectionItem.title
                    parentLayout.setBackgroundColor(ContextCompat.getColor(context, sectionItem.color))
                    secondLayout.recyclerViewChild.adapter = childAdapter
                    childAdapter.addItemList(sectionItem.itemList)
                    sectionItem.initailized = true
                }
            }
        }
        override fun onClick(p0: View?) {
            with(itemView.expandableLayout) {
                if (isExpanded) {
                    collapse()
                } else {
                    expand()
                }
            }
        }

        override fun onLongClick(p0: View?) = false

        override fun onRowItemClick(position: Int, title: String) {
            Toast.makeText(context(), "position : $position, title: $title", Toast.LENGTH_SHORT)
                .show()
            itemView.run {
                with(expandableLayout) {
                    parentLayout.title.text = title
                }
            }
        }
    }
}