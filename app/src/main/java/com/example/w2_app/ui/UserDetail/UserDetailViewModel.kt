package com.example.w2_app.ui.UserDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.w2_app.data.database.FavoriteUserEntity
import com.example.w2_app.data.repository.UserRepository
import com.example.w2_app.data.response.DetailUserResponse
import com.example.w2_app.data.response.FollowingFollowerResponse
import com.example.w2_app.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(private val userRepository: UserRepository) : ViewModel(){
    private val _users = MutableLiveData<DetailUserResponse>()
    val users: LiveData<DetailUserResponse> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userfollowing = MutableLiveData<List<FollowingFollowerResponse>>()
    val userfollowing: LiveData<List<FollowingFollowerResponse>> = _userfollowing

    private val _userfollower = MutableLiveData<List<FollowingFollowerResponse>>()
    val userfollower: LiveData<List<FollowingFollowerResponse>> = _userfollower

    companion object {
        private const val TAG = "UserDetailViewModel"
    }

    fun isFavorite(username: String)=
        userRepository.isFavorite(username)

    fun saveFavorite(favoriteUserEntity: FavoriteUserEntity){
        viewModelScope.launch { userRepository.saveFavorite(favoriteUserEntity) }
    }

    fun deleteFavorite(favoriteUserEntity: FavoriteUserEntity){
        viewModelScope.launch { userRepository.delete(favoriteUserEntity) }
    }
    fun getUserDetail(username : String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")            }
        })
    }

    fun getFollower(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollower(username)
        client.enqueue(object : Callback<List<FollowingFollowerResponse>> {
            override fun onResponse(
                call: Call<List<FollowingFollowerResponse>>,
                response: Response<List<FollowingFollowerResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userfollower.value = response.body()

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<FollowingFollowerResponse>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getFollowing(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<List<FollowingFollowerResponse>> {
            override fun onResponse(
                call: Call<List<FollowingFollowerResponse>>,
                response: Response<List<FollowingFollowerResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userfollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingFollowerResponse>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}

