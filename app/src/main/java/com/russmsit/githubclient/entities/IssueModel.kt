package com.russmsit.githubclient.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueModel(
    val id: Long,
    val name: String,
    val description: String,
    val isClosed: Boolean
) : Parcelable
