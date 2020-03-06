package com.unittestsample.app.presentation.user

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unittestsample.app.R
import com.unittestsample.app.common.extensions.inflate
import com.unittestsample.app.domain.account.entity.User
import kotlinx.android.synthetic.main.row_random_user.view.*

class RandomUserAdapter : RecyclerView.Adapter<RandomUserAdapter.RecyclerViewHolder>() {

    private var arrayList = ArrayList<User>()

    fun setItems(results: ArrayList<User>) {
        arrayList.clear()
        arrayList.addAll(results)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(parent.inflate(R.layout.row_random_user))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.tvUser.text = user.name
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(itemView.ivUser)
        }
    }
}