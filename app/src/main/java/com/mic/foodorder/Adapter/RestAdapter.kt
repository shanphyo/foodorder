package com.mic.foodorder.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.Model.RestaurantX
import com.mic.foodorder.R
import com.mic.webservice.Adapter.RecyclerData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_township.view.*

class RestAdapter(var rd: RecyclerData):RecyclerView.Adapter<RestAdapter.RestViewHolder>() {
    var resultlist:List<RestaurantX> = listOf()
    inner class RestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindRest(result:RestaurantX){
            Picasso.get()
                .load(result.photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img_restaurantpic)
            itemView.txt_restaurantname.text=result.user.name
            itemView.txt_restaurantphno.text=result.phone_no
            itemView.txt_restaurantaddress.text=result.address
            itemView.cv_township.setOnClickListener {
                rd.onFunClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.item_township,parent,false)
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