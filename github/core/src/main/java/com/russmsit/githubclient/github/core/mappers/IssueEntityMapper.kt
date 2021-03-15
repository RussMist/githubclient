package com.russmsit.githubclient.github.core.mappers

import com.russmsit.githubclient.domain.enteties.IssueEntity
import com.russmsit.githubclient.github.core.models.Issue

object IssueEntityMapper {
    fun toIssueEntity(response: Issue): IssueEntity = IssueEntity(
        id = response.id,
        name = response.title ?: "",
        description = response.body ?: "",
        isClosed = (response.state == "closed")
    )
}
