package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") val id: Long = -1,
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("full_name") val fullName: String? = "",
    @SerializedName("owner") val owner: User? = User(),
    @SerializedName("private") val private: Boolean? = false,
    @SerializedName("html_url") val htmlUrl: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("fork") val fork: Boolean? = false,
    @SerializedName("url") val url: String? = "",
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("updated_at") val updatedAt: String? = "",
    @SerializedName("pushed_at") val pushedAt: String? = "",
    @SerializedName("homepage") val homepage: String? = "",
    @SerializedName("size") val size: Long? = -1,
    @SerializedName("stargazers_count") val stargazersCount: Int? = -1,
    @SerializedName("watchers_count") val watchersCount: Int? = -1,
    @SerializedName("language") val language: String? = "",
    @SerializedName("forks_count") val forksCount: Int? = -1,
    @SerializedName("open_issues_count") val openIssuesCount: Int? = -1,
    @SerializedName("master_branch") val masterBranch: String? = "",
    @SerializedName("default_branch") val defaultBranch: String? = "",
    @SerializedName("score") val score: Int? = -1,
    @SerializedName("archive_url") val archiveUrl: String? = "",
    @SerializedName("assignees_url") val assigneesUrl: String? = "",
    @SerializedName("blobs_url") val blobsUrl: String? = "",
    @SerializedName("branches_url") val branchesUrl: String? = "",
    @SerializedName("collaborators_url") val collaboratorsUrl: String? = "",
    @SerializedName("comments_url") val commentsUrl: String? = "",
    @SerializedName("commits_url") val commitsUrl: String? = "",
    @SerializedName("compare_url") val compareUrl: String? = "",
    @SerializedName("contents_url") val contentsUrl: String? = "",
    @SerializedName("contributors_url") val contributorsUrl: String? = "",
    @SerializedName("deployments_url") val deploymentsUrl: String? = "",
    @SerializedName("downloads_url") val downloadsUrl: String? = "",
    @SerializedName("events_url") val eventsUrl: String? = "",
    @SerializedName("forks_url") val forksUrl: String? = "",
    @SerializedName("git_commits_url") val gitCommitsUrl: String? = "",
    @SerializedName("git_refs_url") val gitRefsUrl: String? = "",
    @SerializedName("git_tags_url") val gitTagsUrl: String? = "",
    @SerializedName("git_url") val gitUrl: String? = "",
    @SerializedName("issue_comment_url") val issueCommentUrl: String? = "",
    @SerializedName("issue_events_url") val issueEventsUrl: String? = "",
    @SerializedName("issues_url") val issuesUrl: String? = "",
    @SerializedName("keys_url") val keysUrlsUrl: String? = "",
    @SerializedName("labels_url") val labelsUrl: String? = "",
    @SerializedName("languages_url") val languagesUrl: String? = "",
    @SerializedName("merges_url") val mergesUrl: String? = "",
    @SerializedName("milestones_url") val milestonesUrl: String? = "",
    @SerializedName("notifications_url") val notificationsUrl: String? = "",
    @SerializedName("pulls_url") val pullsUrl: String? = "",
    @SerializedName("releases_url") val releasesUrl: String? = "",
    @SerializedName("ssh_url") val sshUrl: String? = "",
    @SerializedName("stargazers_url") val stargazersUrl: String? = "",
    @SerializedName("statuses_url") val statusesUrl: String? = "",
    @SerializedName("subscribers_url") val subscribersUrl: String? = "",
    @SerializedName("subscription_url") val subscriptionUrl: String? = "",
    @SerializedName("tags_url") val tagsUrl: String? = "",
    @SerializedName("teams_url") val teamsUrl: String? = "",
    @SerializedName("trees_url") val treesUrl: String? = "",
    @SerializedName("clone_url") val cloneUrl: String? = "",
    @SerializedName("mirror_url") val mirrorUrl: String? = "",
    @SerializedName("hooks_url") val hooksUrl: String? = "",
    @SerializedName("svn_url") val svnUrl: String? = "",
    @SerializedName("forks") val forks: Int? = -1,
    @SerializedName("open_issues") val openIssues: Int? = -1,
    @SerializedName("watchers") val watchers: Int? = -1,
    @SerializedName("has_issues") val hasIssues: Boolean? = false,
    @SerializedName("has_projects") val hasProjects: Boolean? = false,
    @SerializedName("has_pages") val hasPages: Boolean? = false,
    @SerializedName("has_wiki") val hasWiki: Boolean? = false,
    @SerializedName("has_downloads") val hasDownloads: Boolean? = false,
    @SerializedName("archived") val archived: Boolean? = false,
    @SerializedName("disabled") val disabled: Boolean? = false,
    @SerializedName("license") val license: License = License()
)