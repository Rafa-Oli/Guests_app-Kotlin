package com.rafa.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rafa.convidados.service.constants.DataBaseConstants

//conexão com banco de dados: extensão de classe SQLiteOpenHelper
class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) { // CRIAÇÃO DO BANCO DE DADOS, CASO N TENHA AINDA
   db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Convidados.db"
        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + "("
                    + DataBaseConstants.GUEST.COLUMNS.ID + "increment primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + "text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + "integer);")
    }
}