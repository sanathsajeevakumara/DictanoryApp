package com.sanathcoding.dictionaryapp.feature_dictionary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanathcoding.dictionaryapp.ui.theme.DictanoryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictanoryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: WordInfoViewModel = hiltViewModel()
                    val state = viewModel.state.value
                    val uiEvent = viewModel.uiEvent
                    val wordInfoItem = state.wordInfoItems
                    val scaffoldState = rememberScaffoldState()
                    val tfText by remember { mutableStateOf(viewModel.searchQuery) }


                    LaunchedEffect(key1 = true) {
                        uiEvent.collectLatest { uiEvent ->
                            when (uiEvent) {
                                is UIEvent.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = uiEvent.message
                                    )
                                }
                            }

                        }
                    }
                    Scaffold(
                        scaffoldState = scaffoldState,
                        content = { padding ->

                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colors.background)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    TextField(
                                        value = tfText.value,
                                        onValueChange = {
                                            viewModel.onSearch(it)
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        placeholder = {
                                            Text(text = "Search...")
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        items(wordInfoItem.size) { item ->
                                            val wordInfo = wordInfoItem[item]
                                            if (item > 0) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                            }
                                            WordInfoItem(wordInfo = wordInfo)
                                            if (item < wordInfoItem.size - 1) {
                                                Divider()
                                            }
                                        }
                                    }
                                }
                                if (state.isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}