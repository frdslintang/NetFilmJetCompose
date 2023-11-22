package com.dicoding.netfilmjetcompose.inject

import com.dicoding.netfilmjetcompose.Repository.NetFilmRepository

object Injection {
    fun provideNetFilmRepository(): NetFilmRepository {
        return NetFilmRepository.getInstance()
    }
}