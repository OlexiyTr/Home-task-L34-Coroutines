package com.example.home_task_l30_rxjavav2.data.repository

import com.example.home_task_l30_rxjavav2.data.BadUserModel
import com.example.home_task_l30_rxjavav2.data.mapper.DataToDomainMapper
import com.example.home_task_l30_rxjavav2.data.mapper.PostResponseToPostDbEntityMapper
import com.example.home_task_l30_rxjavav2.datasource.UserStateConditions
import com.example.home_task_l30_rxjavav2.datasource.api.PostReposApi
import com.example.home_task_l30_rxjavav2.datasource.db.PostsDao
import com.example.home_task_l30_rxjavav2.domain.UserState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class PostsInfoRepositoryTest {

    @Test
    fun `call insertAll if database is empty`(){
        val mockApi = mockk<PostReposApi>(relaxUnitFun = true){
            coEvery { getPostsList() } returns emptyList()
        }

        val mockDao = mockk<PostsDao>(relaxUnitFun = true){
            coEvery { getAllUsersFromDB() } returns emptyList()
        }

        val mockUserStateConditions = mockk<UserStateConditions>{
            every {getBlackList()} returns setOf(
                BadUserModel(1, UserState.NORMAL)
            ) as MutableSet<BadUserModel>
        }

        val mapperToDb = PostResponseToPostDbEntityMapper()
        val mapperToDomain = DataToDomainMapper(mockUserStateConditions)

        val postRepo = PostsInfoRepository(mockApi,mockDao,mapperToDomain,mapperToDb)

        runBlocking {
            postRepo.getPostsFromLocalStorage()
        }

        coVerify { mockDao.insertAll(any()) }
    }
}