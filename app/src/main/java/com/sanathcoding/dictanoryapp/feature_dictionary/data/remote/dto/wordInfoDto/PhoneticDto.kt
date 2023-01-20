package com.sanathcoding.dictanoryapp.feature_dictionary.data.remote.dto.wordInfoDto

data class PhoneticDto(
    val audio: String,
    val license: LicenseDto,
    val sourceUrl: String,
    val text: String
)