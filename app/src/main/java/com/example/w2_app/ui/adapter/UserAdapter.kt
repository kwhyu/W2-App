package com.example.w2_app.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.w2_app.data.response.User
import com.example.w2_app.databinding.RowItemUserBinding
import com.example.w2_app.ui.UserDetail.UserDetailActivity

class UserAdapter:
    ListAdapter <User, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, UserDetailActivity::class.java)
            moveDataUserIntent.putExtra(UserDetailActivity.USER_NAME, user.login)
            moveDataUserIntent.putExtra(UserDetailActivity.avatarUrl, user.avatarUrl)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }

    }
    class MyViewHolder(val binding: RowItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: User){
            binding.tvItemUsername.text = review.login
            Glide.with(binding.ivItemUserPicture.context).load(review.avatarUrl)
                .into(binding.ivItemUserPicture)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}