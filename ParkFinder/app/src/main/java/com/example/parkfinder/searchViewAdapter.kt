package com.example.parkfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class searchViewAdapter(val items:ArrayList<Park>)
    : RecyclerView.Adapter<searchViewAdapter.searchViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(holder:searchViewHolder, view : View, data: Park, position:Int)
    }

    var itemClickListener : OnItemClickListener?= null

    inner class searchViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var parkName: TextView = itemView.findViewById(R.id.parkName)
        var parkAdr : TextView = itemView.findViewById(R.id.parkAdr)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return searchViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: searchViewHolder, position: Int) {
        holder.parkName.text = items[position].parkName

        holder.parkAdr.text = items[position].rdnameAdr
        if(holder.parkAdr.text.equals("")){
            holder.parkAdr.text = items[position].numAdr
        }
    }


}