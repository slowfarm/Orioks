package ru.eva.oriokslive.network.entity.orioks

import com.google.gson.annotations.SerializedName

data class Security(
    @SerializedName("last_used")
    val lastUsed: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user_agent")
    val userAgent: String,
)
