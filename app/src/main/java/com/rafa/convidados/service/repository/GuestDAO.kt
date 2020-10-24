package com.rafa.convidados.service.repository

import androidx.room.*
import com.rafa.convidados.service.model.GuestModel

//metodos para consultas ao banco de dados
@Dao
interface GuestDAO{

    @Insert
    fun save(guestModel: GuestModel): Long
    @Update
    fun update(guestModel: GuestModel): Int
    @Delete
    fun delete(guestModel: GuestModel)

    @Query("SELECT * FROM Guest WHERE id= :id") //get
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guest") //get
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence= 1") //get
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence= 0") //get
    fun getAbsent(): List<GuestModel>



}