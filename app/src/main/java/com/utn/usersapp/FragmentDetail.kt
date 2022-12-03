package com.utn.usersapp

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class FragmentDetail : Fragment(R.layout.fragment_detail), FragmentsDependencies  {
    companion object {
        const val ID_USER = "idUser"
    }

    override var navigate: Navigate? = null
    override lateinit var usersApi: UsersApi

    private lateinit var idUser: String

    private lateinit var viewModel: ViewModelDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idUser = requireArguments().getString(ID_USER)!!

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ViewModelDetail(idUser, usersApi) as T
            }
        }

        viewModel = ViewModelProvider(this, factory).get(ViewModelDetail::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigate = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            val image = user.picture.large ?: ""
            val imageElement: ImageView = view.findViewById(R.id.photo)
            Glide.with(this).load(image).into(imageElement)

            val fullNameElement: TextView = view.findViewById(R.id.full_name)
            fullNameElement.text = "${user.name.title} ${user.name.first} ${user.name.last}"

            val ageElement: TextView = view.findViewById(R.id.age)
            ageElement.text = user.dob.age.toString() + " years old"

            val countryElement: TextView = view.findViewById(R.id.country)
            countryElement.text = user.location.country

            val emailElement: TextView = view.findViewById(R.id.email)
            val sendToIntent: Intent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",
                    user.email,
                    null
                )
            )
            emailElement.text = user.email
            emailElement.setOnClickListener {
                try {
                    startActivity(sendToIntent)
                } catch (e: Throwable) {
                    Log.e("USERAPP", "No se encontró ninguna aplicación para enviar el email")
                }

            }
            emailElement.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            val phoneElement: TextView = view.findViewById(R.id.phone)
            val number = user.phone
            val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            phoneElement.text = number
            phoneElement.setOnClickListener {
                startActivity(callIntent)
            }
            phoneElement.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            val address: TextView = view.findViewById(R.id.address)
            val addressValue = "${user.location.street.name} ${user.location.street.number}, ${user.location.city}"
            val geoIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$addressValue"))
            address.text = addressValue
            address.setOnClickListener {
                startActivity(geoIntent)
            }
            address.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }

        viewModel.getUser()

    }
}