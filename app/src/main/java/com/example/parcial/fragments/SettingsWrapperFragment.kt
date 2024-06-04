package com.example.parcial.fragments

import SettingsFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.parcial.R

class SettingsWrapperFragment : Fragment() {
    /**
     * Fragment that wraps a PreferenceFragmentcompat, allowing
     * usage under a navigation graph.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                replace(R.id.fl_settings, SettingsFragment())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_wrapper, container, false)
    }

}