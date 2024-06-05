package com.example.parcial.activities

import SettingsFragment
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.parcial.R
import com.example.parcial.fragments.SettingsWrapperFragment
import com.example.parcial.helpers.UIHelpers
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener el fragmento de navegación y la vista de navegación
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment

        // Configurar la barra de navegación inferior
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        // Configurar el DrawerLayout y el ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Sincronizar el estado del ActionBarDrawerToggle con el DrawerLayout
        drawerToggle.syncState()

        // Obtener el NavigationView y vincularlo con el NavController
        navigationView = findViewById(R.id.nav_view)
        NavigationUI.setupWithNavController(navigationView, navHostFragment.navController)

        // Configurar el botón del menú hamburguesa para abrir y cerrar el DrawerLayout
        val btnMenu: ImageButton = findViewById(R.id.btn_menu)
        btnMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        // Configurar el modo oscuro
        darkModeSetup()
    }

    private fun darkModeSetup() {
        // Leer Modo oscuro desde las preferencias globales
        val preferencesManager = PreferenceManager.getDefaultSharedPreferences(this)
        val darkModeToggle = getString(R.string.dark_mode_key)
        val toggled = UIHelpers.toggleNightMode(preferencesManager.getBoolean(darkModeToggle, false))
        // Recrear la activity solo si cambio el modo
        if (toggled) this.recreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Manejar los clics del botón de hamburguesa
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
