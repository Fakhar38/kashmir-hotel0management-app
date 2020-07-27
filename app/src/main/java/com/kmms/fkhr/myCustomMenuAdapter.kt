package com.kmms.fkhr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class myCustomMenuAdapter (context : Context, val menuItemList : ArrayList<String>,
                          var onItemClick : (Int) ->Unit = {}) : RecyclerView.Adapter<myCustomMenuAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.menu_item,
            parent, false
        )
        return viewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.displaymenuItem.text = menuItemList[position]
        holder.myContainer.setOnClickListener {
            onItemClick(position)
        }
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displaymenuItem: TextView = itemView.findViewById(R.id.menu_item)
        val myContainer : View = itemView.findViewById(R.id.menu_cb)
    }
}


