package com.vira.echsan.adapters.pembayaran

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.vira.echsan.R
import kotlinx.android.synthetic.main.item_tipe_pembayaran_child_row.view.*

class TipePembayaranChildAdapter(
    private val delegate: ViewHolder.Delegate
) : BaseAdapter(){

    init{
        addSection(ArrayList<String>())
    }

    fun addItemList(itemList: List<ChildItem>) {
        clearSection(0)
        addItemListOnSection(0, itemList)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_tipe_pembayaran_child_row
    override fun viewHolder(layout: Int, view: View) = ViewHolder(delegate, view)

    class ViewHolder(
        private val delegate: Delegate,
        view:View
    ): BaseViewHolder(view){
        interface Delegate{
            fun onRowItemClick(position: Int, title: String, amount: Double)
        }

        private lateinit var title:String
        private var amount: Double? = 0.0

        override fun bindData(data: Any) {
            if (data is ChildItem) {
                title = data.title
                amount = data.amount
                drawItemUI()
            }
        }

        private fun drawItemUI(){
            itemView.run{
                row_title.text = title
            }
        }

        override fun onClick(p0: View?) {
            delegate.onRowItemClick(adapterPosition, this.title, this.amount!!)
        }

        override fun onLongClick(p0: View?): Boolean = false
    }
}