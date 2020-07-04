package com.mic.foodorder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.Model.Menu
import com.mic.foodorder.R
import com.mic.webservice.Adapter.RecyclerData
import com.mic.webservice.Adapter.RecyclerDataMenu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.item_township.view.*

class MenusAdapter(var rd: RecyclerDataMenu) :RecyclerView.Adapter<MenusAdapter.MenusViewHolder>(){
    var count:Int=0
    var resultlist:List<Menu> = listOf()
    inner class MenusViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindMenus(result:Menu){
            itemView.txt_menuname.text=result.menu_name
            itemView.txt_menuprice.text=result.menu_price+" MMK for each"
            Picasso.get()
                .load(result.menu_photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_menupic)

            itemView.img_shoppingicon.setOnClickListener {

                count ++
                itemView.txt_count.text=count.toString()
                rd.onFunClick(result)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenusViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
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