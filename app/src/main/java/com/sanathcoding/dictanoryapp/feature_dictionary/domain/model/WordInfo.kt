package com.sanathcoding.dictanoryapp.feature_dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String?
)
