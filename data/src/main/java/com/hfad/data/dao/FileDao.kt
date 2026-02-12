package com.hfad.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hfad.data.entity.FileEntity

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFile(file: FileEntity)

    @Update
    suspend fun updateFile(file: FileEntity)

    @Query("SELECT * FROM files WHERE id = :id ")
    suspend fun getFileById(id: Int): FileEntity?
}