package com.utn.usersapp

import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.util.Log
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
        val emailAddress = intent.getStringExtra("email")
        val sendToIntent: Intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts( "mailto", emailAddress, null))
        email.text = emailAddress
        email.setOnClickListener {
            try {
                startActivity(sendToIntent)
            } catch (e: Throwable) {
                Log.e("USERAPP", "No se encontró ninguna aplicación para enviar el email")
            }

        }

        val phone: TextView = findViewById(R.id.phone)
        val number = intent.getStringExtra("phone")
        val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        phone.text = number
        phone.setOnClickListener {
            startActivity(callIntent)
        }

        val address: TextView = findViewById(R.id.address)
        val addressValue = intent.getStringExtra("address")
        val geoIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$addressValue"))
        address.text = addressValue
        address.setOnClickListener {
            startActivity(geoIntent)
        }

    }
}