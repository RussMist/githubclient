package com.russmsit.githubclient.domain.enteties

data class IssueEntity(
    val id: Long,
    val name: String,
    val description: String,
    val isClosed: Boolean
)