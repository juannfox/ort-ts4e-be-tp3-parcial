package com.example.parcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.parcial.R
import com.google.android.material.snackbar.Snackbar


class RoundTripFragment : Fragment() {

    lateinit var btnNewDestination : Button
    lateinit var viewRoundTrip: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewRoundTrip = inflater.inflate(R.layout.fragment_round_trip, container, false)
        btnNewDestination = viewRoundTrip.findViewById(R.id.btn_new_destination)

        btnNewDestination.setOnClickListener {
            Snackbar.make(viewRoundTrip, R.string.new_route, Snackbar.LENGTH_SHORT)
                .show()
        }



        return viewRoundTrip
    }



}