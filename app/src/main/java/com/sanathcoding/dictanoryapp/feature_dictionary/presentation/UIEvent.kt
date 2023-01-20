package com.sanathcoding.dictanoryapp.feature_dictionary.presentation

sealed interface UIEvent {
    data class ShowSnackBar(val message: String): UIEvent
}
