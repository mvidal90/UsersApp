package com.utn.usersapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image = intent.getStringExtra("image") ?: ""
        val imageElement: ImageView = findViewById(R.id.photo)
        Glide.with(this).load(image).into(imageElement)

        val fullName: TextView = findViewById(R.id.full_name)
        fullName.text = intent.getStringExtra("fullName")

        val age: TextView = findViewById(R.id.age)
        age.text = intent.getStringExtra("age") + " years old"

        val country: TextView = findViewById(R.id.country)
        country.text = intent.getStringExtra("country")

        val email: TextView = findViewById(R.id.email)
        email.text = intent.getStringExtra("email")

        val phone: TextView = findViewById(R.id.phone)
        phone.text = intent.getStringExtra("phone")

        val address: TextView = findViewById(R.id.address)
        address.text = intent.getStringExtra("address")

    }
}