package com.example.w2_app.di

import android.content.Context
import com.example.w2_app.data.database.FavoriteUserDatabase
import com.example.w2_app.data.repository.UserRepository

object injection {
    fun provideRepository(context: Context): UserRepository {
        val database = FavoriteUserDatabase.getDatabase(context)
        val dao = database.favoriteUserDao()
        return UserRepository.getInstance(dao)}
}