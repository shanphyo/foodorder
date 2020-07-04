package com.mic.foodorder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.Model.Menu
import com.mic.foodorder.R
import com.mic.webservice.Adapter.RecyclerDataMFc
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.item_menuone.view.*

class MFCAdapter(var rdd: RecyclerDataMFc) :RecyclerView.Adapter<MFCAdapter.MenusViewHolder>(){
    var count:Int=0
    var resultlist:List<Menu> = listOf()
    inner class MenusViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindMenus(result:Menu){
            itemView.txt_omenuname.text=result.menu_name
            itemView.txt_omenuprice.text=result.menu_price+" MMK for each"
            itemView.txt_omenuaddress.text="Yangon"
            itemView.txt_omenuphone.text="098899999"
            if(result.menu_photo !=null){
                Picasso.get()
                    .load(result.menu_photo)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(itemView.img_omenupic)
            }
            itemView.img_shoppingiconone.setOnClickListener {
                count ++
                itemView.txt_onecount.text=count.toString()
                rdd.onFunClick(result)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenusViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_menuone,parent,false)
        return MenusViewHolder(view)
    }

    override fun getItemCount(): Int {
       return resultlist.size
    }

    override fun onBindViewHolder(holder: MenusViewHolder, position: Int) {
       holder.bindMenus(resultlist[position])
    }
    fun updateMenuslist(menuslist:List<Menu>){
        this.resultlist=menuslist
        notifyDataSetChanged()
    }
}