package com.example.w2_app.ui.Favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.w2_app.data.repository.UserRepository
import com.example.w2_app.data.response.User

class FavoriteViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val _favoriteUserList = MediatorLiveData<List<User>>()
    val Favuser: LiveData<List<User>> = _favoriteUserList


    private fun getFavoriteUser() {
        val liveData = userRepository.getFavoriteUser()
        _favoriteUserList.addSource(liveData) { Favuser ->
            val convertedList = Favuser.map { Favuser ->
                User(Favuser.username, Favuser.avatarUrl ?: "")
            }
            _favoriteUserList.value = convertedList
        }
    }
    init {
        getFavoriteUser()
    }
}