package com.example.w2_app.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.w2_app.R
import com.example.w2_app.data.DetailUserResponse
import com.example.w2_app.databinding.ActivityUserDetailBinding
import com.example.w2_app.ui.view_model.UserDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity() {
    private lateinit var userDetailBinding: ActivityUserDetailBinding
    private val userDetailViewModel by viewModels<UserDetailViewModel>()
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDetailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(userDetailBinding.root)

        name = intent.getStringExtra(USER_NAME)
        userDetailViewModel.getUserDetail(name.toString())

        userDetailViewModel.users.observe(this) { user ->
            if (user != null) {
                setUserDetailData(user)
            }
        }
        userDetailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = name.toString()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun setUserDetailData(user: DetailUserResponse) {
        userDetailBinding.apply {
            tvNameDetail.visibility = View.VISIBLE
            tvNameDetail.text = user.name

            tvUserNameDetail.visibility = View.VISIBLE
            tvUserNameDetail.text = getString(R.string.username, user.login)

            tvUserFollowersDetail.visibility = View.VISIBLE
            tvUserFollowersDetail.text = getString(R.string.followers, user.followers)

            tvUserFollowingDetail.visibility = View.VISIBLE
            tvUserFollowingDetail.text = getString(R.string.following, user.following)

            ivUserPictureDetail.visibility = View.VISIBLE
            Glide.with(ivUserPictureDetail.context).load(user.avatarUrl)
                .into(ivUserPictureDetail)

            viewPager.visibility = View.VISIBLE
            tabs.visibility = View.VISIBLE
        }
    }
    private fun showLoading(isLoading: Boolean) {
        userDetailBinding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    companion object {
        const val USER_NAME = ""
        private val TAB_TITLES = intArrayOf(
            R.string.followers_tab,
            R.string.following_tab
        )
    }
}
