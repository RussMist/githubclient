package com.russmsit.githubclient.github.core.mappers

import com.russmsit.githubclient.domain.enteties.RepoEntity
import com.russmsit.githubclient.domain.enteties.UserEntity
import com.russmsit.githubclient.github.core.models.Repo

object RepoEntityMapper {
    fun toRepoEntity(response: Repo): RepoEntity =
        RepoEntity(
            id = response.id,
            name = response.name ?: "",
            description = response.description ?: "",
            owner = UserEntity(
                id = response.owner?.id ?: -1,
                name = response.owner?.login ?: "",
                avatarUrl = response.owner?.avatarUrl ?: ""
            )
        )
}
