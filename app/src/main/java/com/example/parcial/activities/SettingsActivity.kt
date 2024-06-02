package com.example.parcial.activities

import android.app.UiModeManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.parcial.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        supportFragmentManager.beginTransaction().replace(R.id.fl_settings, SettingsFragment()).commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // TODO: Reuse
        val prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(getString(R.string.dark_mode_key), false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        companion object {
            fun newInstance() = SettingsFragment()
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_settings, rootKey)

            toggleNightMode()
        }

        private fun toggleNightMode(){
            val darkMode = findPreference<SwitchPreference>(getString(R.string.dark_mode_key))
            darkMode?.apply {
                setDefaultValue(false)
                setOnPreferenceChangeListener { _, lastRead ->
                    // Manejo del modo oscuro con controlador nativo de Material
                    val nightMode = if (lastRead as Boolean) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                    AppCompatDelegate.setDefaultNightMode(nightMode);

                    true
                }
            }
        }
    }
}