package com.example.architectcoderslite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.architectcoderslite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIngresar.setOnClickListener {
            viewModel.onTryLogin(binding.InputEmail.text.toString(), binding.InputPassword.text.toString())
        }


        viewModel.state.observe(this@MainActivity, { state ->
            binding.LayoutEmail.error = state.userError?.let (::getString)
            binding.LayoutPassword.error = state.passError?.let (::getString)
            binding.btnIngresar.visibility = if(state.loggingIn) View.GONE else View.VISIBLE
            binding.progressbar.visibility = if(state.loggingIn) View.VISIBLE else View.GONE


            if(state.loggingIn){
                startActivity(Intent(this@MainActivity, NextActivity::class.java))
                viewModel.onNavigateToNextScreen()                            //Informa que esa accion ya se consumio
            }

        })


    }

}