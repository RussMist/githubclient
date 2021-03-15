package com.russmsit.githubclient.mappers

import com.russmsit.githubclient.domain.enteties.IssueEntity
import com.russmsit.githubclient.domain.enteties.RepoEntity
import com.russmsit.githubclient.entities.IssueModel
import com.russmsit.githubclient.entities.RepoModel

fun RepoEntity.toRepoModel(): RepoModel =
    RepoModel(
        name = this.name,
        description = this.description,
        userName = this.owner.name,
        userAvatarUrl = this.owner.avatarUrl
    )

fun IssueEntity.toIssueModel(): IssueModel =
    IssueModel(
        id = this.id,
        name = this.name,
        description = this.description,
        isClosed = this.isClosed
    )
