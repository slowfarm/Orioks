package ru.eva.oriokslive.network.entity.orioks

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("token") val token: String,
    @SerializedName("error") val error: String? = null,
)
