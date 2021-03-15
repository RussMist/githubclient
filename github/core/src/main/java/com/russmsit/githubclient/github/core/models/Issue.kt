package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("id") val id: Long = -1,
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("repository_url") val repositoryUrl: String? = "",
    @SerializedName("labels_url") val labelsUrl: String? = "",
    @SerializedName("comments_url") val commentsUrl: String? = "",
    @SerializedName("events_url") val eventsUrl: String? = "",
    @SerializedName("html_url") val htmlUrl: String? = "",
    @SerializedName("number") val number: Int? = -1,
    @SerializedName("state") val state: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("body") val body: String? = "",
    @SerializedName("user") val user: User? = User(),
    @SerializedName("labels") val labels: List<Labels> = listOf(),
    @SerializedName("assignee") val assignee: User? = User(),
    @SerializedName("assignees") val assignees: List<User>? = listOf(),
    @SerializedName("milestone") val milestone: Milestone? = Milestone(),
    @SerializedName("locked") val locked: Boolean? = false,
    @SerializedName("active_lock_reason") val activeLockReason: String? = "",
    @SerializedName("comments") val comments: Int? = -1,
    @SerializedName("pull_request") val pullRequest: PullRequest? = PullRequest(),
    @SerializedName("closed_at") val closed_at: String? = "",
    @SerializedName("created_at") val created_at: String? = "",
    @SerializedName("updated_at") val updated_at: String? = "",
    @SerializedName("author_association") val author_association: String? = ""
)