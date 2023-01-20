package com.sanathcoding.dictionaryapp.feature_dictionary.data.remote.dto.wordInfoDto

import com.sanathcoding.dictionaryapp.feature_dictionary.domain.model.License

data class LicenseDto(
    val name: String?,
    val url: String?
) {
    fun toLicense(): License {
        return License(
            name = name,
            url = url
        )
    }
}