package com.example.w2_app.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.w2_app.data.FollowingFollowerResponse
import com.example.w2_app.databinding.RowItemUserBinding

class FollowingFollowerAdapter :
    ListAdapter<FollowingFollowerResponse, FollowingFollowerAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val follow = getItem(position)
        holder.bind(follow)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, UserDetailActivity::class.java)
            moveDataUserIntent.putExtra(UserDetailActivity.USER_NAME, follow.login)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }
    }
    class MyViewHolder(val binding: RowItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: FollowingFollowerResponse){
            binding.tvItemUsername.text = review.login
            Glide.with(binding.ivItemUserPicture.context).load(review.avatarUrl)
                .into(binding.ivItemUserPicture)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowingFollowerResponse>() {
            override fun areItemsTheSame(oldItem: FollowingFollowerResponse, newItem: FollowingFollowerResponse): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FollowingFollowerResponse, newItem: FollowingFollowerResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}