package com.dicoding.netfilmjetcompose.Repository

import com.dicoding.netfilmjetcompose.model.Film
import com.dicoding.netfilmjetcompose.model.FilmsData


class NetFilmRepository {

    private val filmList = mutableListOf<Film>()

    init {
        if (filmList.isEmpty()) {
            FilmsData.films.forEach {
                filmList.add(it)
            }
        }
    }

    fun getFilms(): List<Film> {
        return FilmsData.films
    }

    fun searchFilms(query: String): List<Film>{
        return FilmsData.films.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getFilmByName(name: String): Film {
        return filmList.first {
            it.name == name
        }
    }

    companion object {
        @Volatile
        private var instance: NetFilmRepository? = null

        fun getInstance(): NetFilmRepository =
            instance ?: synchronized(this) {
                NetFilmRepository().apply {
                    instance = this
                }
            }
    }
}
