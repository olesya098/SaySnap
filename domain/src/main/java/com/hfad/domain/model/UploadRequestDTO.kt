package com.hfad.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadRequestDTO (
    @SerialName("upload_url")
    var uploadUrl: String
)