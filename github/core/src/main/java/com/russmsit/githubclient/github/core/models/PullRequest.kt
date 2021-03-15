package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("url") val url: String? = "",
    @SerializedName("html_url") val htmlUrl: String? = "",
    @SerializedName("diff_url") val diffUrl: String? = "",
    @SerializedName("patch_url") val patchUrl: String? = ""
)