package com.example.parcial.activities

import SettingsFragment
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.parcial.R
import com.example.parcial.fragments.SettingsWrapperFragment
import com.example.parcial.helpers.UIHelpers
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragment container con navgraph
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
        // Barra/menu inferior
        bottomNavView = findViewById(R.id.bottom_bar)
        // Conectar navgraph con menu inferior
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        renderSettingsFragment() // Solo si el intent tiene el parametro esperado

        darkModeSetup() // Lectura de preferencias globales para modo oscuro
    }

    private fun renderSettingsFragment(){
        // Ver si mostrar 5 elemento de navegacion, SettingsFragment
        // La bottom bar soporta solo 4, pero usamos el mismo nav controller
        if (intent.extras != null){
            val isSettings = intent.extras?.getBoolean("settings", false)
            navHostFragment.navController.navigate(R.id.settings)
        }
    }

    private fun darkModeSetup(){
        // Leer Modo oscuro desde las preferencias globales
        val preferencesManager = PreferenceManager.getDefaultSharedPreferences(this);
        val darkModeToggle = getString(R.string.dark_mode_key)
        val toggled = UIHelpers.toggleNightMode(preferencesManager.getBoolean(darkModeToggle, false));
    }
}
