package com.example.home_task_l30_rxjavav2.data.repository

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.data.mapper.DataToDomainMapper
import com.example.home_task_l30_rxjavav2.data.mapper.PostResponseToPostDbEntityMapper
import com.example.home_task_l30_rxjavav2.datasource.api.PostReposApi
import com.example.home_task_l30_rxjavav2.datasource.db.PostsDao
import com.example.home_task_l30_rxjavav2.datasource.model.PostResponse
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostsInfoRepository @Inject constructor(
    private val api: PostReposApi,
    private val dao: PostsDao,
    private val mapperToDB: PostResponseToPostDbEntityMapper,
    private val dataToDomainMapper: DataToDomainMapper
) {

    suspend fun getPostsFromLocalStorage(): List<PostDomainModel>? {
        val posts = updateLocalStorage()
        return dataToDomainMapper.map(posts)
    }

    private suspend fun updateLocalStorage(): List<PostData> {
        val posts = dao.getAllUsersFromDB()
        if (posts.isEmpty()) {
            dao.insertAll(mapperToDB.map(api.getPostsList()))
            return dao.getAllUsersFromDB()
        }
        return posts
    }

    suspend fun putNewPost(post: PostData) {
        dao.insertPost(post)
    }

    suspend fun getNewPostId() = dao.getIdForNewPost() + 1
}

