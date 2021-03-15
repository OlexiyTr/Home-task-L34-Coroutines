package com.example.home_task_l30_rxjavav2.data.mapper

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.data.BadUserModel
import com.example.home_task_l30_rxjavav2.datasource.UserStateConditions
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.domain.UserState
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import io.mockk.every
import org.junit.jupiter.api.Test
import io.mockk.mockk
import junit.framework.TestCase.assertEquals

internal class DataToDomainMapperTest {

    private val id = 1
    private val userId = 1
    private val title = "title"
    private val body = "body"
    private val addedFrom = AddedFrom.SERVER

    @Test
    fun `map data post to domain post with normal state`() {
        val expectedPostList = listOf(
            PostDomainModel(userId,id,title,body,UserState.NORMAL,addedFrom)
        )

        val dataPostList = listOf(
            PostData(userId,id,title,body,addedFrom)
        )

        val mockUserState = mockk<UserStateConditions>{
            every {getBlackList()} returns setOf(
                BadUserModel(userId,UserState.NORMAL)
            ) as MutableSet<BadUserModel>
        }

        val dataToDomainMapper = DataToDomainMapper(mockUserState)

        val actualPostList = dataToDomainMapper.map(dataPostList)

        assertEquals(expectedPostList, actualPostList)
    }

    @Test
    fun `map data post to domain post with has warning state`() {

        val expectedPostList = listOf(
            PostDomainModel(userId,id,title,body,UserState.HAS_WARNING,addedFrom)
        )

        val dataPostList = listOf(
            PostData(userId,id,title,body,addedFrom)
        )

        val mockUserState = mockk<UserStateConditions>{
            every {getBlackList()} returns setOf(
                BadUserModel(userId,UserState.HAS_WARNING)
            ) as MutableSet<BadUserModel>
        }

        val dataToDomainMapper = DataToDomainMapper(mockUserState)

        val actualPostList = dataToDomainMapper.map(dataPostList)

        assertEquals(expectedPostList, actualPostList)
    }

    @Test
    fun `map data post to domain post with banned state`() {

        val expectedPostList = listOf(
            PostDomainModel(userId,id,title,body,UserState.BANNED,addedFrom)
        )

        val dataPostList = listOf(
            PostData(userId,id,title,body,addedFrom)
        )

        val mockUserState = mockk<UserStateConditions>{
            every {getBlackList()} returns setOf(
                BadUserModel(userId,UserState.BANNED)
            ) as MutableSet<BadUserModel>
        }

        val dataToDomainMapper = DataToDomainMapper(mockUserState)

        val actualPostList = dataToDomainMapper.map(dataPostList)

        assertEquals(expectedPostList, actualPostList)
    }

}