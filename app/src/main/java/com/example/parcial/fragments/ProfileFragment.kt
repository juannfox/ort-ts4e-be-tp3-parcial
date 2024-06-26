package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.parcial.activities.MainActivity
import com.example.parcial.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setupMenu()
    }


    private fun setupMenu(){
        /**
         * Configurar Menu de este fragmento
          */
        // Boton de settings
        val settingsItem: Int = 2
        binding.menuProfile.menu.getItem(settingsItem).setOnMenuItemClickListener {
            // Llamar a la main activity, pero enviando parametro
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("settings", true)
            startActivity(intent)
            true
        }

        // Botones no implementados
        for (i in intArrayOf(0, 1, 3)) {
            binding.menuProfile.menu.getItem(i).setOnMenuItemClickListener {
                val itemName = binding.menuProfile.menu.getItem(i).title.toString()
                Toast.makeText(context, "Unimplemented: $itemName", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}