package com.sanathcoding.dictionaryapp.feature_dictionary.presentation

import com.sanathcoding.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    var wordInfoItems: List<WordInfo> = emptyList(),
    var isLoading: Boolean = false
)
