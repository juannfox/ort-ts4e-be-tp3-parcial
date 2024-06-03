package com.example.parcial.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.example.parcial.R
import com.example.parcial.helpers.UIHelpers

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        supportFragmentManager.beginTransaction().replace(R.id.fl_settings, SettingsFragment()).commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Leer Modo oscuro desde las preferencias globales
        val preferencesManager = PreferenceManager.getDefaultSharedPreferences(this);
        val darkModeToggle = getString(R.string.dark_mode_key)
        UIHelpers.toggleNightMode(preferencesManager.getBoolean(darkModeToggle, false));
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        companion object {
            fun newInstance() = SettingsFragment()
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_settings, rootKey)

            configureNightModeSetting()
        }

        private fun configureNightModeSetting(){
            val darkMode = findPreference<SwitchPreferenceCompat>(getString(R.string.dark_mode_key))
            darkMode?.apply {
                setDefaultValue(false)
                setOnPreferenceChangeListener { _, lastRead ->
                    // Prender o Apagar Modo oscuro
                    UIHelpers.toggleNightMode(lastRead as Boolean);

                    true
                }
            }
        }
    }
}