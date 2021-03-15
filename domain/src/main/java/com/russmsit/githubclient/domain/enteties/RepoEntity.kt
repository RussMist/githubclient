package com.russmsit.githubclient.domain.enteties

data class RepoEntity(
    val id: Long,
    val name: String,
    val description: String,
    val owner: UserEntity
)
