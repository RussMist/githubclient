package com.russmsit.githubclient.github.core.models

import com.google.gson.annotations.SerializedName

data class Labels(
    @SerializedName("id") val id: Long = -1,
    @SerializedName("node_id") val nodeId: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("color") val color: String? = "",
    @SerializedName("default") val default: Boolean? = false
)