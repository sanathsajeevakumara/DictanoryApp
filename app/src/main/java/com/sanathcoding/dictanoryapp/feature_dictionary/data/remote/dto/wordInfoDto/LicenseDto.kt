package com.sanathcoding.dictanoryapp.feature_dictionary.data.remote.dto.wordInfoDto

import com.sanathcoding.dictanoryapp.feature_dictionary.domain.model.License

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