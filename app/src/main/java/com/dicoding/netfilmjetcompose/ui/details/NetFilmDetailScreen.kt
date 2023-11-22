package com.dicoding.netfilmjetcompose.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

@Composable
fun NetFilmDetailScreen(
    filmName: String,
    modifier: Modifier = Modifier,
    viewModel: NetFilmDetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideNetFilmRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFilmByName(filmName)
            }
            is UiState.Success -> {
                NetFilmDetailContent(
                    film = uiState.data,
                    modifier = modifier,
                    navigateBack = navigateBack
                )
            }
            is UiState.Error ->  stringResource(R.string.error_message)
            else -> {}

        }
    }
}
@Composable
fun NetFilmDetailContent(
    film: Film,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .size(48.dp)
                .clickable { navigateBack() }
                .padding(16.dp)
        )
        NetFilmDetailBanner(
            name = film.name,
            photoUrl = film.photoUrl,
            rating = film.rating,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .width(250.dp)
                .height(350.dp)
        )
        NetFilmDetailDescription(
            name = film.name,
            genre = film.genres,
            rating = film.rating,
            sinopsis  = film.sinopsis,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NetFilmDetailContentPreview() {
    NetFilmTheme {
        NetFilmDetailContent(
            film = FilmsData.films[0],
            navigateBack = {}
        )
    }
}
