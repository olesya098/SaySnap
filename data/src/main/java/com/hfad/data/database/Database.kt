package com.hfad.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hfad.data.dao.FileDao
import com.hfad.data.entity.FileEntity

@Database(entities = [FileEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun fileDao(): FileDao
}