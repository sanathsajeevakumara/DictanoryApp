package com.sanathcoding.dictanoryapp.feature_dictionary.domain.repository

import com.sanathcoding.dictanoryapp.core.util.Resource
import com.sanathcoding.dictanoryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}