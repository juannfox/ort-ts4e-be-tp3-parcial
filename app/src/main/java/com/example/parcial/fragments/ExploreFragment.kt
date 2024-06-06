package com.example.parcial.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.activities.DestinationDetailActivity
import com.example.parcial.adapters.DestinationAdapter
import com.example.parcial.databinding.FragmentExploreBinding
import com.example.parcial.entities.Destination
import com.example.parcial.listeners.OnViewItemClickedListener
import com.example.parcial.viewmodel.ExploreViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(), OnViewItemClickedListener<Destination> {

    private val MAIN_DESTINATION = "Paris-Francia"

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
        binding = FragmentExploreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)

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
            if (isChecked) {
                Log.d("ExploreFragment", "isChecked")
                viewModel.addFavourite(MAIN_DESTINATION)
            } else {
                Log.d("ExploreFragment", "is des-Checked")
                viewModel.removeFavourite(MAIN_DESTINATION)
            }
        }

        viewModel.isFavourite.observe(viewLifecycleOwner, Observer { isSavedValue ->
            Log.d("ExploreFragment", "isFav")
            if (isSavedValue) {
                binding.destinationLikeButton.isChecked = true
            }
        })

        /*viewModel.isSaved.observe(viewLifecycleOwner, Observer { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_saved_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        viewModel.isDeleted.observe(viewLifecycleOwner, Observer { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_deleted_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })*/
    }

    private fun loadDestinations() {
        destinations.add(
            Destination(
                "Baros",
                "Maldives",
                1299,
                "Spend 5 days and 4 nights in one of the best islands in the world! Bask in the sun while walking in the white sand beach and enjoy the night partying at the popular seaside bars.",
                "1D2N",
                mutableListOf(
                    "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0d/56/71/a6/baros-aerial-view.jpg?w=1200&h=-1&s=1",
                    "https://cf.bstatic.com/xdata/images/hotel/max1024x768/440909107.jpg?k=13ecd4217f7049d36f460ccea8b3f4324eb40561e18c9692bda2d3264bfeec80&o=&hp=1",
                    "https://www.baros.com/es/wp-content/uploads/sites/9/2021/05/FB_Share.jpg"
                )
            )
        )
        destinations.add(
            Destination(
                "Bali",
                "Indonesia",
                3500,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eu tempus diam, vitae aliquam dolor. Aliquam facilisis urna id sem commodo gravida. Proin eu lectus condimentum, volutpat sem ac, facilisis nulla. Integer porta luctus quam, ac ultrices sem euismod eu. Donec vehicula mauris vel dolor commodo, sit amet blandit ex condimentum. In vel dui non elit placerat scelerisque. Suspendisse consectetur enim sed accumsan elementum.",
                "2D3N",
                mutableListOf(
                    "https://s3-alpha-sig.figma.com/img/db9e/a9d8/830a9cd7f332253ecada83b60351d78c?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=p92twg8iyd9gok38bvMwfasncSk2qKUN~pzeOcvnGLgJBAAowvx5Fg8QthstcN4K084-Kd0~prIXVJGs~oCeOzr8ePdfF9XAcBHEprCjPtRlSAPnCZ8TPsIUby0ELdKxHiv-RBg1OqUDxI4ZvFLJXlfZ7Sgp1j15NvRnc85oRhDaVybz7tRVlGK6ZlRmp~E03cbOH8yQlbmfD2x6P3fyANFDP~n1ztZM4w8prNAk~67z2vdTum~hYTZyOwe8FytX9hNOHPwlIaq3QRORVQdPpVcHnzB8kvqAz4C9fTCQATnboYuebQBKILVse~DgRAciSHbn0VpBH~tCbK2gJf0ziw__",
                    "https://www.travelandleisure.com/thmb/KE0vV7K8Ngvc_7j-_FGx_jCUdCM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/TAL-bali-lead-image-BALITG1223-101f43c88c044081a4558b737afbd094.jpg",
                    "https://media.iatiseguros.com/wp-content/uploads/2018/05/04005529/bali-que-hacer-Templo-Ulun-Danu.jpg"
                )
            )
        )
        destinations.add(
            Destination(
                "Palawan",
                "Philippines",
                620,
                "Morbi ex quam, vehicula id interdum pharetra, fermentum a felis. Nunc consequat, libero quis vestibulum porta, sem est sodales felis, quis hendrerit enim lorem eu neque. Etiam hendrerit pulvinar odio. Nulla in facilisis enim. Sed auctor lectus nec aliquam interdum. Vestibulum nec lacus pretium, mattis dolor in, iaculis justo. Praesent eu neque ligula. Fusce quis leo in diam tempus aliquam vel sit amet magna. Aliquam erat volutpat. Mauris porta lectus ut lacus interdum ultricies. Aenean finibus nibh a molestie dapibus.",
                "4D3N",
                mutableListOf(
                    "https://s3-alpha-sig.figma.com/img/117d/79f3/3638fb7469e83bae9aedc5201481b6af?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=RBicmOwSRQYrS0g8YeBiH2XUMNBvGRU1Z3UWscB8nzBbaynzQzzk-NdDUruOl1EPAW6UrfTAFPDdTJmPUx4nP9GVfI99PXhCkSIJp4XZagFtPUOkBeSpuhYKr8GYzaeiHGJAByCPZt7AqcNfFoR810y0CnKDXOZ3W3CEswdmyEHADwhQmvYqZMeDW0LsoWkYFVsHOIEmCvNbi~habaAaNVX4C3q0UXXkgYKfop8GA4BG85x9RdSAosCoDpw7Jjmuh~LPFQxXkZTf3E7QFKkGs4y5bLtUr7L6xq5MZOsUAD7B8ZlsIkprqrJXGx1~ldvcLa0yctUhvaIJKZe5elIs~Q__",
                    "https://i.natgeofe.com/n/eb6538cf-b879-4307-b3b8-c121b2a7b540/double-outrigger-boats-called-banca-in-palawan-philippines_4x3.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/1/13/Kayangan_Lake%2C_Coron_-_Palawan.jpg"
                )
            )
        )
        destinations.add(
            Destination(
                "Buenos Aires",
                "Argentina",
                1234,
                "Curabitur venenatis nunc dui, nec consequat turpis suscipit sit amet. Ut ac pharetra velit. Quisque laoreet orci turpis, sed malesuada felis facilisis non. Nunc metus nunc, tincidunt ac tincidunt eu, ullamcorper at ligula. Maecenas et dignissim lacus. Vivamus a risus faucibus, interdum nulla sed, commodo ligula. Vivamus nisi urna, viverra quis placerat ut, efficitur id lacus.",
                "3D4N",
                mutableListOf(
                    "https://images.pexels.com/photos/20897698/pexels-photo-20897698/free-photo-of-ciudad-punto-de-referencia-noche-rascacielos.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    "https://media.staticontent.com/media/pictures/77797799-6ce3-42b4-92b4-38d73009e47b",
                    "https://www.fodors.com/wp-content/uploads/2018/08/HERO-BA-Instagrammable-Neighborhood-La-Boca-18.jpg"
                )
            )
        )
    }

    override fun onViewItemDetail(destination: Destination) {
        val intent = Intent(context, DestinationDetailActivity::class.java).apply {
            putExtra("destinationDetail", destination);
            putExtra("mainImage", destination.images[0])
            putStringArrayListExtra("images", destination.images as ArrayList<String>)
        }
        startActivity(intent);
    }
}