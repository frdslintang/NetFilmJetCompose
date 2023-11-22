package com.dicoding.netfilmjetcompose.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.netfilmjetcompose.Repository.NetFilmRepository
import com.dicoding.netfilmjetcompose.ui.details.NetFilmDetailViewModel
import com.dicoding.netfilmjetcompose.ui.film.NetFilmsViewModel


class ViewModelFactory(private val repository: NetFilmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(NetFilmsViewModel::class.java) ->
                    NetFilmsViewModel(repository)
                isAssignableFrom(NetFilmDetailViewModel::class.java) ->
                    NetFilmDetailViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}