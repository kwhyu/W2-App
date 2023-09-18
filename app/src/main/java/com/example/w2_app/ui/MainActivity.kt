package com.example.w2_app.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w2_app.data.User
import com.example.w2_app.databinding.ActivityMainBinding
import com.example.w2_app.ui.view_model.MainViewModel

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
    private fun setUsersData(users: List<User>) {
        val adapter = UserAdapter()
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}