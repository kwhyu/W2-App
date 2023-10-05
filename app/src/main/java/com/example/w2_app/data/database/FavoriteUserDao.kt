package com.example.w2_app.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Query("SELECT * FROM Favuser")
    fun getAllFavoriteUser(): LiveData<List<FavoriteUserEntity>>
    @Query("SELECT * FROM Favuser WHERE username = :username")
    fun isFavorite(username: String):LiveData<FavoriteUserEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteUser: FavoriteUserEntity)
    @Delete
    suspend fun delete(favoriteUser: FavoriteUserEntity)


}