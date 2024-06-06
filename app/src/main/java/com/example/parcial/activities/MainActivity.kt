package com.example.parcial.activities

import SettingsFragment
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.databinding.ActivityDestinationDetailBinding
import com.example.parcial.databinding.ActivityMainBinding
import com.example.parcial.domain.FavouriteUseCase
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import com.example.parcial.fragments.SettingsWrapperFragment
import com.example.parcial.helpers.UIHelpers
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavView: BottomNavigationView

    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var destinationDetailPhotoAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragment container con navgraph
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
        // Barra/menu inferior
        bottomNavView = findViewById(R.id.bottom_bar)
        // Conectar navgraph con menu inferior
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        darkModeSetup()
    }

    private fun darkModeSetup(){
        // Leer Modo oscuro desde las preferencias globales
        val preferencesManager = PreferenceManager.getDefaultSharedPreferences(this);
        val darkModeToggle = getString(R.string.dark_mode_key)
        val toggled = UIHelpers.toggleNightMode(preferencesManager.getBoolean(darkModeToggle, false));
        // Recrear la activity solo si cambio el modo
        if (toggled) this.recreate()
    }
}

