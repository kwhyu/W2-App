package com.example.w2_app.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w2_app.R
import com.example.w2_app.data.response.User
import com.example.w2_app.databinding.ActivityMainBinding
import com.example.w2_app.ui.Favorite.FavoriteActivity
import com.example.w2_app.ui.adapter.UserAdapter
import com.example.w2_app.utils.DarkModeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            svFinduser.setupWithSearchBar(sbFinduser)
            svFinduser.editText.setOnEditorActionListener { oh, _, _ ->
                sbFinduser.text = svFinduser.text
                mainViewModel.getUser(oh.text.toString())
                svFinduser.hide()
                    false
            }
        }
        mainViewModel.users.observe(this) { users ->
            setUsersData(users)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.fav -> {
                val moveIntent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.theme -> {
                val moveIntent = Intent(this@MainActivity, DarkModeActivity::class.java)
                startActivity(moveIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun setUsersData(users: List<User>) {
        val adapter = UserAdapter()
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}