package com.example.home_task_l30_rxjavav2.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home_task_l30_rxjavav2.PostData

@Dao
interface PostsDao {

    @Query("SELECT * FROM PostData")
    suspend fun getAllUsersFromDB(): List<PostData>

    @Query("SELECT MAX(id) FROM PostData")
    suspend fun getIdForNewPost(): Int

    @Insert
    suspend fun insertPost(userPostData:PostData)

    @Insert
    suspend fun insertAll(list: List<PostData>)
}