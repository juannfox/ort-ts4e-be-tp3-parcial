import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.parcial.R
import com.example.parcial.helpers.UIHelpers

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
                requireActivity().recreate()

                true
            }
        }
    }
}