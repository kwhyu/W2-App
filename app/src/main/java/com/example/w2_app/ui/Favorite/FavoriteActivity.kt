package com.example.w2_app.ui.Favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w2_app.databinding.ActivityFavoriteBinding
import com.example.w2_app.ui.ModelFactory.ViewModelFactory
import com.example.w2_app.ui.adapter.FavoriteAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoritebinding: ActivityFavoriteBinding

    private lateinit var favoriteAdapter: FavoriteAdapter

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoritebinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoritebinding.root)


        val factory: ViewModelFactory = ViewModelFactory.getInstance(application)
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        favoriteViewModel.Favuser.observe(this) {
            if (!it.isNullOrEmpty()){
                val layoutManager = LinearLayoutManager(this)
                favoritebinding.rvUserFav.layoutManager = layoutManager
                favoriteAdapter = FavoriteAdapter(it)
                favoritebinding.rvUserFav.adapter = favoriteAdapter
            }else{
                favoritebinding.rvUserFav.visibility = View.GONE
            }
        }

    }
}