package com.example.shopapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionDao {
    @Query("SELECT * from transactions")
    fun getAll() : List<Transaction>

    @Insert()
    fun insertAll(vararg transaction: Transaction)

    @Delete()
    fun delete(transaction: Transaction)

    @Update()
    fun update(vararg transaction: Transaction)
}