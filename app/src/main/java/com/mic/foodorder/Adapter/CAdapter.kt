package com.mic.foodorder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.Model.CategoryX
import com.mic.foodorder.R
import com.mic.webservice.Adapter.RecyclerDataForCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CAdapter (var rd:RecyclerDataForCategory):RecyclerView.Adapter<CAdapter.CViewHolder>(){
    var cList:List<CategoryX> = listOf()
    fun updateResultlist(resultlist:List<CategoryX>){
        this.cList =resultlist
        notifyDataSetChanged()

    }
    inner class CViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindC(c:CategoryX){
            itemView.txt_categoryname.text=c.category_name
            Picasso.get()
                .load(c.category_photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_category)
            itemView.cv_category.setOnClickListener {
                rd.onFunClick(c)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolder =
    CViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
    )

    override fun getItemCount(): Int = cList.size


    override fun onBindViewHolder(holder: CViewHolder, position: Int) {
        holder.bindC(cList[position])
    }
}