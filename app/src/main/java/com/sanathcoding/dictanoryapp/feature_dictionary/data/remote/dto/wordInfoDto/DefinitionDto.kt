package com.sanathcoding.dictanoryapp.feature_dictionary.data.remote.dto.wordInfoDto

import com.sanathcoding.dictanoryapp.feature_dictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String?,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}