package com.dicoding.netfilmjetcompose.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.netfilmjetcompose.Repository.NetFilmRepository
import com.dicoding.netfilmjetcompose.di.UiState
import com.dicoding.netfilmjetcompose.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetFilmDetailViewModel(
    private val repository: NetFilmRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Film>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Film>>
        get() = _uiState

    fun getFilmByName(filmName: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFilmByName(filmName))
        }
    }



}