package com.sanathcoding.dictanoryapp.feature_dictionary.presentation

import com.sanathcoding.dictanoryapp.core.util.Resource
import com.sanathcoding.dictanoryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    var wordInfoItems: List<WordInfo> = emptyList(),
    var isLoading: Boolean = false
)
