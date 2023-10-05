package com.example.w2_app.data.repository

import androidx.lifecycle.LiveData
import com.example.w2_app.data.database.FavoriteUserDao
import com.example.w2_app.data.database.FavoriteUserEntity

class UserRepository(
    private val userdao : FavoriteUserDao,
) {
    suspend fun saveFavorite(favoriteUserEntity: FavoriteUserEntity) {
        userdao.insert(favoriteUserEntity)
    }
    suspend fun delete(favoriteUserEntity: FavoriteUserEntity) {
        userdao.delete(favoriteUserEntity)
    }
    fun getFavoriteUser(): LiveData<List<FavoriteUserEntity>> = userdao.getAllFavoriteUser()

    fun isFavorite(username : String): LiveData<FavoriteUserEntity>{
        return userdao.isFavorite(username)
    }
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userdao: FavoriteUserDao,
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userdao)
        }.also { instance = it }
    }
}