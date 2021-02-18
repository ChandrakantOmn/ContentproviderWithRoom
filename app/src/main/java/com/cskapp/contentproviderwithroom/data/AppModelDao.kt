package com.cskapp.contentproviderwithroom.data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cskapp.contentproviderwithroom.data.AppModel

@Dao
interface AppModelDao {
    @Query("SELECT COUNT(*) FROM " + AppModel.TABLE_NAME)
    fun count(): Int

    @Insert
    fun insert(appModel: AppModel?): Long

    @Insert
    fun insertAll(appModels: Array<AppModel?>?): LongArray?

    @Query("SELECT * FROM " + AppModel.TABLE_NAME)
    fun selectAll(): Cursor?

    @Query("SELECT * FROM " + AppModel.TABLE_NAME + " WHERE " + AppModel.COLUMN_ID + " = :id")
    fun selectById(id: Long): Cursor?

    @Query("DELETE FROM " + AppModel.TABLE_NAME + " WHERE " + AppModel.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(appModel: AppModel?): Int
}