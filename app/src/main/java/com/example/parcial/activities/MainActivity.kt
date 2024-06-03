package com.example.parcial.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.parcial.R
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

        // Leer Modo oscuro desde las preferencias globales
        val preferencesManager = PreferenceManager.getDefaultSharedPreferences(this);
        val darkModeToggle = getString(R.string.dark_mode_key)
        UIHelpers.toggleNightMode(preferencesManager.getBoolean(darkModeToggle, false));
    }
}
