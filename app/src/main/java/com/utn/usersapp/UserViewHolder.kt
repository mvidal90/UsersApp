package com.utn.usersapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.photo)
    val fullName = itemView.findViewById<TextView>(R.id.full_name)
    val age = itemView.findViewById<TextView>(R.id.age)
    val country = itemView.findViewById<TextView>(R.id.country)
}