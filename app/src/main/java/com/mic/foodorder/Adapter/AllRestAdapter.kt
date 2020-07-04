package com.mic.foodorder.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.Model.RestaurantX
import com.mic.foodorder.R
import com.mic.webservice.Adapter.RecyclerData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_all.view.*
import kotlinx.android.synthetic.main.item_township.view.*

class AllRestAdapter(var rd: RecyclerData):RecyclerView.Adapter<AllRestAdapter.RestViewHolder>() {
    var resultlist:List<RestaurantX> = listOf()
    inner class RestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindRest(result:RestaurantX){
            Picasso.get()
                .load(result.photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_allpic)
            itemView.txt_allname.text=result.user.name
            itemView.txt_allphone.text=result.phone_no
            itemView.txt_alladdress.text=result.address
            itemView.cv_all.setOnClickListener {
                rd.onFunClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.item_all,parent,false)
        return  RestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  resultlist.size
    }

    override fun onBindViewHolder(holder: RestViewHolder, position: Int) {
       holder.bindRest(resultlist[position])
    }
    fun updateRestaurantlist(relist:List<RestaurantX>){
        this.resultlist=relist
        notifyDataSetChanged()
    }
}