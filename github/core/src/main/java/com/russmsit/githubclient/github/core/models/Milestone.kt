package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class Milestone(
    @SerializedName("url") val url: String? = "",
    @SerializedName("html_url") val htmlUrl: String? = "",
    @SerializedName("labels_url") val labelsUrl: String? = "",
    @SerializedName("id") val id: Long = -1,
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("number") val number: Int? = -1,
    @SerializedName("state") val state: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("creator") val creator: User? = User(),
    @SerializedName("open_issues") val openIssues: Int? = -1,
    @SerializedName("closed_issues") val closedIssues: Int? = -1,
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("updated_at") val updatedAt: String? = "",
    @SerializedName("closed_at") val closedAt: String? = "",
    @SerializedName("due_on") val dueOn: String? = ""
)