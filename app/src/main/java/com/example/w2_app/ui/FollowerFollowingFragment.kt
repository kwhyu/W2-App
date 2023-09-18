package com.example.w2_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w2_app.data.FollowingFollowerResponse
import com.example.w2_app.databinding.FragmentFollowerBinding
import com.example.w2_app.ui.view_model.UserDetailViewModel


class FollowerFollowingFragment : Fragment() {
    private  val userDetailViewModel by activityViewModels<UserDetailViewModel>()
    private lateinit var FollowersBinding: FragmentFollowerBinding

    private var username: String? = null
    private var position: Int? = null
    private lateinit var errorMsg: String

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        FollowersBinding = FragmentFollowerBinding.inflate(layoutInflater)
        return FollowersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }

        userDetailViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        FollowersBinding.rvFollowerfollowing.layoutManager = layoutManager


        if (position == 1) {
            userDetailViewModel.getFollower(username.toString())
            userDetailViewModel.userfollower.observe(requireActivity()) { followers ->
                setFollower(followers)

                if (followers.size <= 0){
                    errorMsg = "Data tidak ditemukan"
                    FollowersBinding.tvErrorid.text = errorMsg
                    FollowersBinding.tvErrorid.visibility = View.VISIBLE
                    Toast.makeText(requireActivity(), "Data tidak ditemukan", Toast.LENGTH_LONG).show()

                }else{
                    FollowersBinding.tvErrorid.visibility = View.INVISIBLE
                    setFollower(followers)
                    Toast.makeText(requireActivity(), "Data ditemukan", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            userDetailViewModel.getFollowing(username.toString())
            userDetailViewModel.userfollowing.observe(requireActivity()) { following ->
                if (following.size <= 0){
                    errorMsg = "Data tidak ditemukan"
                    FollowersBinding.tvErrorid.text = errorMsg
                    FollowersBinding.tvErrorid.visibility = View.VISIBLE
                    Toast.makeText(requireActivity(), "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                }else{
                    FollowersBinding.tvErrorid.visibility = View.INVISIBLE
                    setFollowing(following)
                    Toast.makeText(requireActivity(), "Data ditemukan", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setFollowing(following: List<FollowingFollowerResponse>) {
        val adapter = FollowingFollowerAdapter()
        adapter.submitList(following)
        FollowersBinding.rvFollowerfollowing.adapter = adapter
        FollowersBinding.rvFollowerfollowing.visibility = View.VISIBLE
    }

    private fun setFollower(followers: List<FollowingFollowerResponse>) {
        val adapter = FollowingFollowerAdapter()
        adapter.submitList(followers)
        FollowersBinding.rvFollowerfollowing.adapter = adapter
        FollowersBinding.rvFollowerfollowing.visibility = View.VISIBLE
    }
    private fun showLoading(isLoading: Boolean) {
        FollowersBinding.progressBar3.visibility = if (isLoading) View.GONE else View.GONE
    }

    companion object {
        const val ARG_POSITION: String = "arg_position"
        const val ARG_USERNAME: String = "arg_username"
    }
}