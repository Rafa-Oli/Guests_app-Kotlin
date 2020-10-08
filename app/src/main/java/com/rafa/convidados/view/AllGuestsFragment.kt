package com.rafa.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rafa.convidados.R
import com.rafa.convidados.view.adapter.GuestAdapter
import com.rafa.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
                ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView
        //1- Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        //2 - Definir um layout: como se comporta na tela com o código
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter : Layouts + dados
        recycler.adapter = mAdapter

        observer()
        allGuestsViewModel.load()
        return root
    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer{
            mAdapter.updateGuests(it)

        })
    }
}