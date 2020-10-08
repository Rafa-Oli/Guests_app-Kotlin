package com.rafa.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafa.convidados.R
import com.rafa.convidados.service.model.GuestModel
import com.rafa.convidados.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {
    private var mGuestList: List<GuestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(
        holder: GuestViewHolder,
        position: Int
    ) {// momento que depois de criado os dados, ele atribui para o layout
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int { // quantos elementos possue
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()

    }


}