package com.utn.usersapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
                return ViewModelDetail(idUser) as T
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

        //viewModel.pelicula.observe(viewLifecycleOwner) { pelicula ->
            // Obtenemos referencia a la etiqueta de texto del titulo
            //val titulo: TextView = view.findViewById(R.id.title)
            //titulo.text = pelicula.titulo

            // Obtenemos referencia a la imagen del poster
            //val imagen: ImageView = view.findViewById(R.id.photo)
            //Glide.with(this).load(pelicula.imagen).into(imagen)
        //}


        viewModel.getUser()

    }
}