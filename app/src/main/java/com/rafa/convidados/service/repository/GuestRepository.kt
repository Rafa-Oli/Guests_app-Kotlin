package com.rafa.convidados.service.repository

import android.content.Context

import com.rafa.convidados.service.model.GuestModel

class GuestRepository (context: Context) {

    //acesso ao banco de dados
    private val mDataBase= GuestDataBase.getDatabase(context).guestDAO()




    fun getAll(): List<GuestModel> { // traz todos os convidados
return mDataBase.getInvited()

    }

    fun getPresent(): List<GuestModel> { // traz todos os convidados

        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> { // traz todos os convidados

        return mDataBase.getAbsent()
    }

    fun get(id: Int): GuestModel? {
  return mDataBase.get(id)
  }

    //CRUD - Create, Read, Update, Delete
    fun save(guest: GuestModel): Boolean {
       return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0


    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }
}