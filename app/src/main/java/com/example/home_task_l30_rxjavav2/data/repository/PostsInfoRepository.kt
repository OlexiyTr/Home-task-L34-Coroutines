package com.example.home_task_l30_rxjavav2.data.repository

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.data.mapper.DataToDomainMapper
import com.example.home_task_l30_rxjavav2.data.mapper.PostResponseToPostDbEntityMapper
import com.example.home_task_l30_rxjavav2.datasource.api.PostReposApi
import com.example.home_task_l30_rxjavav2.datasource.db.PostsDao
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostsInfoRepository @Inject constructor(
    private val api : PostReposApi,
    private val dao: PostsDao,
    private val dataToDomainMapper: DataToDomainMapper,
    private val postResponseToPostDbEntityMapper: PostResponseToPostDbEntityMapper
) {

    suspend fun getPostsFromLocalStorage(): List<PostDomainModel>? = withContext(Dispatchers.IO) {
        val posts = dao.getAllUsersFromDB()
        if (posts.isEmpty()){
            dao.insertAll(postResponseToPostDbEntityMapper.map(api.getPostsList()))
            dao.getAllUsersFromDB()
        }
        posts
        dataToDomainMapper.map(posts)
    }

    suspend fun putNewPost(post: PostData) = withContext(Dispatchers.IO){
        dao.insertPost(post)
    }

    suspend fun getNewPostId() = dao.getIdForNewPost() + 1
}

