package com.rafa.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafa.convidados.R
import com.rafa.convidados.service.model.GuestModel
import kotlinx.android.synthetic.main.row_guest.view.*

//armazena referencias aos elementos de interfaces
class GuestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(guest: GuestModel){
       val textName =  itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

    }


}