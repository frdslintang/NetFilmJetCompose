package com.dicoding.netfilmjetcompose.ui.film

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.netfilmjetcompose.R
import com.dicoding.netfilmjetcompose.di.UiState
import com.dicoding.netfilmjetcompose.inject.Injection
import com.dicoding.netfilmjetcompose.model.Film
import com.dicoding.netfilmjetcompose.model.FilmsData
import com.dicoding.netfilmjetcompose.ui.factory.ViewModelFactory
import com.dicoding.netfilmjetcompose.ui.theme.NetFilmTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NetFilmsApp(
    modifier: Modifier = Modifier,
    viewModel: NetFilmsViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideNetFilmRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFilm(query)
            }
            is UiState.Success -> {
                NetFilmContent(
                    listFilm = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::getAllFilm
                )
            }
            is UiState.Error -> stringResource(R.string.error_message)
            else -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NetFilmContent(
    listFilm: List<Film>,
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Column(modifier = modifier) {
        RoundedSearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        Box(modifier = modifier) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.testTag("listFilm"),
                content = {
                    val groupedFilms = listFilm.groupBy { it.name.first().toUpperCase() }
                    groupedFilms.keys.sorted().forEach { groupKey ->
                        stickyHeader {
                            CharacterHeader(groupKey.toChar())
                        }
                        items(groupedFilms[groupKey] ?: emptyList()) { data ->
                            NetFilmListItem(
                                name = data.name,
                                rating = data.rating,
                                photoUrl = data.photoUrl,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navigateToDetail(data.name)
                                    }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun CharacterHeader(
    char: Char,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CharacterHeaderPreview() {
    NetFilmTheme {
        CharacterHeader('A')
    }
}

@Preview(showBackground = true)
@Composable
fun NetFilmContentPreview() {
    NetFilmTheme {
        NetFilmContent(
            listFilm = FilmsData.films,
            query = "",
            onQueryChange = {},
            navigateToDetail = {}
        )
    }
}

