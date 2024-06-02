package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parcial.R
import com.example.parcial.activities.SettingsActivity
import com.example.parcial.databinding.FragmentOffersBinding

class OffersFragment : Fragment() {

    private lateinit var binding: FragmentOffersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Boton temporal para ir a Settings mientras se arma esta pantalla
        binding.btSettings.setOnClickListener(){
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}