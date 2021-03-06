package com.rafa.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafa.convidados.R
import com.rafa.convidados.service.model.GuestModel
import com.rafa.convidados.view.listener.GuestListener
import com.rafa.convidados.view.viewholder.GuestViewHolder

// recycler é tipo de listagem capaz de criar layout sufuciente para preencher a tela, uma vez preenchido a tela ele é reciclado, tornando mais performatica em relação ao ListView
class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {
    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {// linha do convidado na lista

        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item,mListener)
    }

    override fun onBindViewHolder(
        holder: GuestViewHolder,
        position: Int
    ) {// momento que depois de criado os dados, ele atribui os valores para o layout
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int { // quantos elementos possue
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
  }
    fun attachListener(listener: GuestListener){
        mListener = listener
    }


}