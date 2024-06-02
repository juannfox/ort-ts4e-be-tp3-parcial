package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.activities.DestinationDetailActivity
import com.example.parcial.adapters.DestinationAdapter
import com.example.parcial.adapters.TripAdapter
import com.example.parcial.databinding.FragmentExploreBinding
import com.example.parcial.databinding.FragmentSearchBinding
import com.example.parcial.entities.Destination
import com.example.parcial.entities.Trip
import com.example.parcial.listeners.OnViewItemClickedListener
import com.example.parcial.services.APIClient
import com.example.parcial.services.FlightInterface

class ExploreFragment : Fragment(), OnViewItemClickedListener<Destination> {

    lateinit var exploreView: View

    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var destinationAdapter: RecyclerView.Adapter<*>
    private lateinit var destinations: MutableList<Destination>
    private lateinit var binding: FragmentExploreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentExploreBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        exploreView = binding.root
        return  binding.root
        //return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerView = binding.trendingDestinationRecycler
        recyclerView.setHasFixedSize(true)
        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = manager

        // Adapter
        destinations = mutableListOf() // Inicialmente vacia
        destinationAdapter = DestinationAdapter(destinations, this@ExploreFragment)
        recyclerView.adapter = destinationAdapter

        loadDestinations()

        binding.destinationLikeButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if(isChecked){
                Toast.makeText(context, "Like :)", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context, "Dislike :(", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadDestinations(){
        destinations.add(Destination("Baros", "Maldives", 1299, "","1D2N", mutableListOf("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0d/56/71/a6/baros-aerial-view.jpg?w=1200&h=-1&s=1")))
        destinations.add(Destination("Bali", "Indonesia", 3500, "","2D3N", mutableListOf("https://s3-alpha-sig.figma.com/img/db9e/a9d8/830a9cd7f332253ecada83b60351d78c?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=p92twg8iyd9gok38bvMwfasncSk2qKUN~pzeOcvnGLgJBAAowvx5Fg8QthstcN4K084-Kd0~prIXVJGs~oCeOzr8ePdfF9XAcBHEprCjPtRlSAPnCZ8TPsIUby0ELdKxHiv-RBg1OqUDxI4ZvFLJXlfZ7Sgp1j15NvRnc85oRhDaVybz7tRVlGK6ZlRmp~E03cbOH8yQlbmfD2x6P3fyANFDP~n1ztZM4w8prNAk~67z2vdTum~hYTZyOwe8FytX9hNOHPwlIaq3QRORVQdPpVcHnzB8kvqAz4C9fTCQATnboYuebQBKILVse~DgRAciSHbn0VpBH~tCbK2gJf0ziw__")))
        destinations.add(Destination("Palawan", "Philippines", 620, "","4D3N", mutableListOf("https://s3-alpha-sig.figma.com/img/117d/79f3/3638fb7469e83bae9aedc5201481b6af?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=RBicmOwSRQYrS0g8YeBiH2XUMNBvGRU1Z3UWscB8nzBbaynzQzzk-NdDUruOl1EPAW6UrfTAFPDdTJmPUx4nP9GVfI99PXhCkSIJp4XZagFtPUOkBeSpuhYKr8GYzaeiHGJAByCPZt7AqcNfFoR810y0CnKDXOZ3W3CEswdmyEHADwhQmvYqZMeDW0LsoWkYFVsHOIEmCvNbi~habaAaNVX4C3q0UXXkgYKfop8GA4BG85x9RdSAosCoDpw7Jjmuh~LPFQxXkZTf3E7QFKkGs4y5bLtUr7L6xq5MZOsUAD7B8ZlsIkprqrJXGx1~ldvcLa0yctUhvaIJKZe5elIs~Q__")))
        destinations.add(Destination("Buenos Aires", "Argentina", 1234, "","3D4N", mutableListOf("https://images.pexels.com/photos/20897698/pexels-photo-20897698/free-photo-of-ciudad-punto-de-referencia-noche-rascacielos.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")))
    }

    override fun onViewItemDetail(destination: Destination) {
        val intent = Intent(context, DestinationDetailActivity::class.java);
        intent.putExtra("destinationDetail", destination);
        intent.putExtra("destImg",destination.images[0])
        startActivity(intent);
    }

}