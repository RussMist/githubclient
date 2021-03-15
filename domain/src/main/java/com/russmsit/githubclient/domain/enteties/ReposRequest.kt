package com.russmsit.githubclient.domain.enteties

data class ReposRequest(
    val searchingQuery: String,
    val pageNumber: Int,
    val perPage: Int,
    val sort: String = "stars"
)