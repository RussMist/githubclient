package com.russmsit.githubclient.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoModel(
    val name: String,
    val description: String,
    val userName: String,
    val userAvatarUrl: String
) : Parcelable