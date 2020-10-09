package com.rafa.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rafa.convidados.R
import com.rafa.convidados.service.constants.GuestConstants
import com.rafa.convidados.view.adapter.GuestAdapter
import com.rafa.convidados.view.listener.GuestListener
import com.rafa.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            s: Bundle?
    ): View? {
        mViewModel=
                ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView
        //1- Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        //2 - Definir um layout: como se comporta na tela com o código
        recycler.layoutManager = LinearLayoutManager(context)

        //3 - Definir um adapter : Layouts + dados
        recycler.adapter = mAdapter

        mListener = object: GuestListener{
            override fun onClick(id: Int) {
             val intent=   Intent(context, GuestFormActivity::class.java)
                val bundle= Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)

                mViewModel.load(GuestConstants.FILTER.EMPTY)

            }
        }
        mAdapter.attachListener(mListener) // atribuiu o evento

        observer()

        return root
    }

   override fun onResume(){
       super.onResume()
       mViewModel.load(GuestConstants.FILTER.EMPTY)
   }
    private fun observer(){
        mViewModel.guestList.observe(viewLifecycleOwner, Observer{
            mAdapter.updateGuests(it)

        })
    }
}