package com.rafa.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafa.convidados.R
import com.rafa.convidados.service.model.GuestModel
import com.rafa.convidados.view.listener.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

//armazena referencias aos elementos de interfaces
class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name
        //quando o textName for clicado ele dispara um evento:
        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        //clique mais demorado
        textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context).setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover).setPositiveButton(R.string.remover){
                    dialog, which -> listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar,null)
                .show()

            true
        }

    }


}