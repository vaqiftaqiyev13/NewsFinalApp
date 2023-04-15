package com.vagif_tagiyev.newsfinalapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vagif_tagiyev.newsfinalapp.model.Article

@Database(
    entities = [Article::class],
    version = 1
)

abstract class NewsDatabase : RoomDatabase() {
    abstract fun getAllNews(): NewsDAO


    companion object {


        @Volatile
        private var instance: NewsDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "newsDatabase"
            ).build()
    }


}