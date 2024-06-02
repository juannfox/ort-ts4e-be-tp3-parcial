package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.parcial.activities.SearchResultsActivity
import com.example.parcial.databinding.FragmentSearchBinding
import com.example.parcial.entities.Trip
import com.example.parcial.listeners.OnViewItemClickedListener

class SearchFragment : Fragment() {

    lateinit var searchView: View
    lateinit var btnSearch: Button
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentSearchBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        searchView = binding.root
        return  searchView
    }

    override fun onStart() {
        super.onStart()

        btnSearch = binding.btnSearch

        btnSearch.setOnClickListener(){
            val intent = Intent(context, SearchResultsActivity::class.java)
            startActivity(intent)
        }

    }



}