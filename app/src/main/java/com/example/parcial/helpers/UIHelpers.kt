package com.example.parcial.helpers

import androidx.appcompat.app.AppCompatDelegate

class UIHelpers {

    companion object {
        fun toggleNightMode(condition: Boolean){
            /**
             * Activar o desactivar el modo oscuro
             */
            val mode = if (condition) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            // Manejo del modo oscuro con controlador nativo de Material
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }
}