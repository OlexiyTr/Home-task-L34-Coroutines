package com.example.home_task_l30_rxjavav2.ui

import com.example.home_task_l30_rxjavav2.R
import com.example.home_task_l30_rxjavav2.data.repository.ResourceRepository
import com.example.home_task_l30_rxjavav2.datasource.model.AddedFrom
import com.example.home_task_l30_rxjavav2.domain.UserState
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DomainToUiMapperTest {

    private val id = 1
    private val userId = 1
    private val title = "title"
    private val body = "body"
    private val normalColor = 1
    private val warningColor = 2
    private val mockResourceRepository = mockk<ResourceRepository> {
        every { getColor(R.color.white) } returns normalColor
        every { getColor(R.color.red) } returns warningColor
    }

    @Test
    fun `map domain post to ui post`() {

        val mapper = DomainToUiMapper(mockResourceRepository)

        val domainPostsList = listOf(
            PostDomainModel(userId, id, title, body, UserState.NORMAL, AddedFrom.USER),
            PostDomainModel(userId, id, title, body, UserState.HAS_WARNING, AddedFrom.USER),
            PostDomainModel(userId, id, title, body, UserState.BANNED, AddedFrom.USER)
        )

        val actualPostList = mapper.map(domainPostsList)

        val examplePostsList = listOf(
            PostUiModel.StandardPostUiModel(id, userId.toString(), title, body, false, normalColor),
            PostUiModel.StandardPostUiModel(id, userId.toString(), title, body, true, warningColor),
            PostUiModel.BannedUserPostUiModel(id, userId)
        )

        assertEquals(examplePostsList, actualPostList)
    }
}