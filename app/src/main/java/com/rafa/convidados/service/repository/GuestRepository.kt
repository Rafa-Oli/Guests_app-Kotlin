package com.rafa.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.rafa.convidados.service.constants.DataBaseConstants
import com.rafa.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context) {
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository // responsavel por guardar a instancia

        //para instanciar uma classe, ja que o construtor é private
        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) { // verificação se ja foi inicializada
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    fun getAll(): List<GuestModel> { // traz todos os convidados
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> { // traz todos os convidados
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> { // traz todos os convidados
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD - Create, Read, Update, Delete
    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase  //inserindo dado no banco
            val contentValue = ContentValues() // classe que pode adicionar valores para ela
            contentValue.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name) // chave-valor
            contentValue.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )  // 0 para falso e 1 para verdadeiro
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValue)
            true
        } catch(e: Exception){
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase  //inserindo dado no banco
            val contentValues = ContentValues() // classe que pode adicionar valores para ela
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name) // chave-valor
            contentValues.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args= arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues,selection, args)
            true
        } catch(e: Exception){
            false
        }


    }

    fun delete(guest: GuestModel) {

    }
}