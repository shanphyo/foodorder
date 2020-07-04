package com.mic.foodorder.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mic.foodorder.R
import com.mic.foodorder.database.Card
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter :RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    var total:Double=0.0
    private  var carslist= emptyList<Card>()
    inner class CardViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindCard(card:Card){
            itemView.card_no.text=card.cid.toString()
            itemView.card_name.text=card.cname.toString()
            itemView.card_price.text=card.cprice.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  carslist.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCard(carslist[position])
    }
    fun setCard(books:List<Card>){
        this.carslist=books
        notifyDataSetChanged()
    }
    fun setTotal():String{
        var total=total
        return total.toString()
    }
}