package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.parcial.activities.SearchResultsActivity
import com.example.parcial.adapters.FlightSearchStateAdapter
import com.example.parcial.databinding.FragmentSearchBinding
import com.example.parcial.entities.Trip
import com.example.parcial.listeners.OnViewItemClickedListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    lateinit var searchView: View
    lateinit var btnSearch: Button
    lateinit var searchViewPager: ViewPager2
    lateinit var tabLayout: TabLayout
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewPager = binding.viewPagerSearchFlight
        tabLayout = binding.tabLayoutSearchFlight

        searchViewPager.adapter = FlightSearchStateAdapter(requireActivity())

        TabLayoutMediator(tabLayout, searchViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "One Way"
                1 -> "Round Trip"
                else -> null
            }
        }.attach()

        btnSearch = binding.btnSearch

        btnSearch.setOnClickListener(){
            val intent = Intent(context, SearchResultsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

    }



}