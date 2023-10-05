package com.example.w2_app.ui.ModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.w2_app.data.repository.UserRepository
import com.example.w2_app.di.injection
import com.example.w2_app.ui.Favorite.FavoriteViewModel
import com.example.w2_app.ui.UserDetail.UserDetailViewModel

class ViewModelFactory private constructor(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> return UserDetailViewModel(userRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> return FavoriteViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(injection.provideRepository(context))
            }.also { instance = it }
    }
}