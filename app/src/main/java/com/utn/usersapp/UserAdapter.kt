package com.utn.usersapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(
    private val context: Context,
    private val onCLickUser: (User) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    private var users: List<User> = emptyList()

    fun setUsers(usersList: List<User>) {
        this.users = usersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(
            R.layout.user_item,
            parent,
            false
        )
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        Glide.with(context).load(user.picture.medium).into(holder.image)
        holder.fullName.text = "${user.name.title} ${user.name.first} ${user.name.last}"
        holder.age.text = user.dob.age.toString() + " years old"
        holder.country.text = user.location.country

        holder.itemView.setOnClickListener { onCLickUser(user) }
    }
    override fun getItemCount(): Int {
        return users.size
    }
}