package com.vagif_tagiyev.newsfinalapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vagif_tagiyev.newsfinalapp.model.Article

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(newsArticle: Article) : Long

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<Article>>

    @Delete
    suspend fun deleteNews(newsArticle: Article)


}