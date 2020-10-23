package com.rafa.convidados.service.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class GuestDataBase: RoomDatabase(){
    companion object{

        private lateinit var  INSTANCE: GuestDataBase
        fun getDatabase(context: Context ): GuestDataBase{
            if(!::INSTANCE.isInitialized){
                INSTANCE =  Room.databaseBuilder(context, GuestDataBase::class.java,"guestDB")
                        .allowMainThreadQueries()
                        .build() // cria a instancia do banco
            }
            return INSTANCE

        }
    }
}