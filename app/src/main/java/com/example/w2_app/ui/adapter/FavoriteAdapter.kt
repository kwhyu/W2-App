package com.example.w2_app.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.w2_app.data.response.User
import com.example.w2_app.databinding.RowItemUserBinding
import com.example.w2_app.ui.UserDetail.UserDetailActivity

class FavoriteAdapter(private val UserList: List<User>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    inner class FavoriteAdapterViewHolder(private val binding: RowItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: User){
            binding.tvItemUsername.text = review.login
            Glide.with(binding.ivItemUserPicture.context).load(review.avatarUrl)
                .into(binding.ivItemUserPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        return FavoriteAdapterViewHolder(
            RowItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        val user = UserList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val moveDataUserIntent = Intent(holder.itemView.context, UserDetailActivity::class.java)
            moveDataUserIntent.putExtra(UserDetailActivity.USER_NAME, user.login)
            moveDataUserIntent.putExtra(UserDetailActivity.avatarUrl, user.avatarUrl)
            holder.itemView.context.startActivity(moveDataUserIntent)
        }
    }
}
