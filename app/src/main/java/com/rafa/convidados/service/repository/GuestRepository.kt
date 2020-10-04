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
 return try {
            val db = mGuestDataBaseHelper.readableDatabase  //inserindo dado no banco

            //db.rawQuery("select * from Guest where id = $id",null) //não é seguro por mais que seja mais rapido

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            ) // nomes de colunas que quer retornar


            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID));
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //pegando o nome do convidado
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }


            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

    }

    fun getPresent(): List<GuestModel> { // traz todos os convidados
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase  //inserindo dado no banco

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID));
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //pegando o nome do convidado
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
  }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

    }

    fun getAbsent(): List<GuestModel> { // traz todos os convidados
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase  //inserindo dado no banco

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID));
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //pegando o nome do convidado
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }


            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

    }

    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase  //inserindo dado no banco

            //db.rawQuery("select * from Guest where id = $id",null) //não é seguro por mais que seja mais rapido

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            ) // nomes de colunas que quer retornar
            val selection =
                DataBaseConstants.GUEST.COLUMNS.ID + " = ?" //condição = se a coluna ID foi igual ao args(ID do convidado)
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //pegando o nome do convidado
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }

            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }

    }

    //CRUD - Create, Read, Update, Delete
    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase  //inserindo dado no banco

            val contentValue = ContentValues() // classe que pode adicionar valores para ela
            contentValue.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name) // chave-valor
            contentValue.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)  // 0 para falso e 1 para verdadeiro
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValue)
            true
        } catch (e: Exception) {
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
            val selection =
                DataBaseConstants.GUEST.COLUMNS.ID + " = ?" //condição = se a coluna ID foi igual ao args(ID do convidado)
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }


    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase  //inserindo dado no banco

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }
}