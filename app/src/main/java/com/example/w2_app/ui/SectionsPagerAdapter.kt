package com.example.w2_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = FollowerFollowingFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowerFollowingFragment.ARG_POSITION, position + 1)
            putString(FollowerFollowingFragment.ARG_USERNAME, username)
        }
        return fragment
    }
}