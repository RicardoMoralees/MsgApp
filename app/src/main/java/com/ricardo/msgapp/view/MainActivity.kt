package com.ricardo.msgapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ricardo.msgapp.databinding.ActivityMainBinding
import com.ricardo.msgapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.btnBuscar.setOnClickListener {

            Log.e("MainActivity", "btnBuscar")
            binding.pbBuscando.showProgressBar()

            val texto = binding.searchVBuscarDigimon.query.toString()
            if (texto.isNotEmpty()) {
                mainActivityViewModel.getDigimon(texto)!!.observe(this, Observer { response ->

                    if (response.success!!) {
                        val digimon = response.respuesta?.get(0)
                        val nombre = digimon?.name
                        val imagen = digimon?.img

                        binding.ivImagen.visibility = AppCompatImageView.VISIBLE
                        binding.txtVNombreDigimon.visibility = AppCompatImageView.VISIBLE
                        Glide.with(context).load(imagen).apply(RequestOptions.overrideOf(300).circleCrop()).into(binding.ivImagen)
                        binding.txtVNombreDigimon.text = nombre
                        binding.pbBuscando.hideProgressBar()
                    } else {
                        showErrorDialog(response.errorMsg.toString())
                    }
                })
            } else {
                showErrorDialog("Escriba algo en el campo")
            }

        }
    }

    fun showErrorDialog(mensaje: String) {
        binding.ivImagen.visibility = AppCompatImageView.INVISIBLE
        binding.txtVNombreDigimon.visibility = AppCompatImageView.INVISIBLE
        AlertDialog.Builder(context)
            .setTitle("Atención!")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, id ->
                dialog.dismiss()
            }
            .create().show()
        binding.pbBuscando.hideProgressBar()
    }
}