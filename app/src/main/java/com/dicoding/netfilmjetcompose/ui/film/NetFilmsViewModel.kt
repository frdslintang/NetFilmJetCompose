package com.dicoding.netfilmjetcompose.ui.film


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dicoding.netfilmjetcompose.Repository.NetFilmRepository
import com.dicoding.netfilmjetcompose.di.UiState
import com.dicoding.netfilmjetcompose.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetFilmsViewModel(private val repository: NetFilmRepository) : ViewModel() {
    private val _groupedFilms = MutableStateFlow<Map<Char, List<Film>>>(emptyMap())
    val groupedFilms: StateFlow<Map<Char, List<Film>>> get() = _groupedFilms

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _uiState = MutableStateFlow<UiState<List<Film>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Film>>> get() = _uiState

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val films = repository.getFilms()
                    .sortedBy { it.name }
                    .groupBy { it.name[0] }
                _groupedFilms.value = films
                _uiState.value = UiState.Success(films.values.flatten())
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load films: ${e.message}")
            }
        }
    }

    fun getAllFilm(newQuery: String) {
        _query.value = newQuery
        try {
            val filteredFilm = repository.getFilms().filter {
                it.name.contains(_query.value, ignoreCase = true)
            }
            _uiState.value = UiState.Success(filteredFilm)
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message.toString())
        }
    }




}


