package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class SuitableReposResponse(
    @SerializedName("total_count") val totalCount: Int? = -1,
    @SerializedName("incomplete_results") val incompleteResults: Boolean? = false,
    @SerializedName("items") val items: List<Repo>? = listOf()
)