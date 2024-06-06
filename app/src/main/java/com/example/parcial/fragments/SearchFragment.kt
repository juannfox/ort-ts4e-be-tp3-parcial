package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.parcial.R
import com.example.parcial.activities.SearchResultsActivity
import com.example.parcial.adapters.FlightSearchStateAdapter
import com.example.parcial.adapters.OtherOffersAdapter
import com.example.parcial.databinding.FragmentSearchBinding
import com.example.parcial.entities.Offer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    lateinit var searchView: View
    lateinit var btnSearch: Button
    lateinit var searchViewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var recOffers: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var offerAdapter: OtherOffersAdapter
    private lateinit var offers: MutableList<Offer>
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentSearchBinding.inflate(layoutInflater)
        searchView = binding.root

        searchViewPager = binding.viewPagerSearchFlight
        tabLayout = binding.tabLayoutSearchFlight

        setupViewPager(searchViewPager)
        TabLayoutMediator(tabLayout, searchViewPager) { tab, position ->
            tab.customView = createCustomTabView(position)

        }.attach()


        btnSearch = binding.btnSearch

        btnSearch.setOnClickListener(){
            val intent = Intent(context, SearchResultsActivity::class.java)
            startActivity(intent)
        }


        setUpRecyclerOffer()
        loadOffers()

        return  searchView
    }

    fun setUpRecyclerOffer(){

        recOffers = binding.recyclerviewOffersHorizontal
        recOffers.setHasFixedSize(true)
        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recOffers.layoutManager = manager

        offers = mutableListOf()
        offerAdapter = OtherOffersAdapter(offers)
        recOffers.adapter = offerAdapter

    }

    fun loadOffers(){
        offers.add(Offer("", "20% discount for mastercard users", "limited time offer!", "", "https://i.ibb.co/zmKtCtq/offer-cardv.png", "", 25, "VISA"))
        offers.add(Offer("", "25% discount with your visa credit cards", "limited time offer!", "", "https://i.ibb.co/QvrfXLH/offer-cardm.png", "",20, "MASTERCARD"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ajustar el tamaño del ViewPager2 al cambiar de pestaña
        searchViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                searchViewPager.requestLayout()
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedTabTextView = (tab?.customView as? TextView)
                selectedTabTextView?.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val unselectedTabTextView = (tab?.customView as? TextView)
                unselectedTabTextView?.setTextColor(ContextCompat.getColor(requireContext(), R.color.fickle_grey))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }


    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = FlightSearchStateAdapter(requireActivity())
        adapter.addFragment(OneWayFragment())
        adapter.addFragment(RoundTripFragment())
        viewPager.adapter = adapter
    }

    private fun createCustomTabView(position: Int): View {
        val customTab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        when (position) {
            0 -> {
                customTab.text = "One way"
                customTab.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            1 -> {
                customTab.text = "Round trip"
                customTab.setTextColor(ContextCompat.getColor(requireContext(), R.color.fickle_grey))
            }
        }

        return customTab
    }



    override fun onStart() {
        super.onStart()

    }




}