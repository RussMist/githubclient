package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("key") val key: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("spdx_id") val spdxId: String? = "",
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("html_url") val htmlUrl: String? = ""
)
