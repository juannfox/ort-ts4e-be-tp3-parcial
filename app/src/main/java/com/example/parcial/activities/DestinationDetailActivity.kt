package com.example.parcial.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.R
import com.example.parcial.adapters.DetinationDetailPhotoAdapter
import com.example.parcial.databinding.ActivityDestinationDetailBinding
import com.example.parcial.entities.Destination
import com.example.parcial.viewmodel.DestinationDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback


@AndroidEntryPoint
class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var destinationDetailPhotoAdapter: RecyclerView.Adapter<*>
    private lateinit var destinationPhotos: MutableList<String>
    private lateinit var destinationItem: Destination

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        destinationItem = intent.getParcelableExtra<Destination>("destinationDetail")!!

        val viewModel by viewModels<DestinationDetailViewModel>(
            extrasProducer = {
                defaultViewModelCreationExtras.withCreationCallback<
                        DestinationDetailViewModel.DestinationDetailViewModelFactory> { factory ->
                    factory.create(destinationItem)
                }
            }
        )

        val destImg = intent.getStringExtra("mainImage")

        Glide.with(this).load(destImg).centerCrop().into(binding.destinationBackgroundImage)
        binding.destinationName.text = destinationItem?.destinationName
        binding.destinationCity.text = destinationItem?.city
        binding.destinationPrice.text = "$${destinationItem?.price}"
        binding.destinationOverview.text = destinationItem?.overview
        binding.destinationDuration.text = destinationItem?.duration

        binding.destinationLikeButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                viewModel.addFavourite("${destinationItem?.destinationName}-${destinationItem?.city}")
            } else {
                viewModel.removeFavourite("${destinationItem?.destinationName}-${destinationItem?.city}")
            }
        }

        viewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        viewModel.isFavourite.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                binding.destinationLikeButton.isChecked = true
            }
        })

        /*viewModel.isSaved.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                Toast.makeText(this, "Destination ${destinationItem?.destinationName} like", Toast.LENGTH_SHORT).show()
                Snackbar.make(binding.root, R.string.favourite_saved_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })*/

        viewModel.isDeleted.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                Toast.makeText(
                    this,
                    "Destination ${destinationItem?.destinationName} dislike",
                    Toast.LENGTH_SHORT
                ).show()
                Snackbar.make(binding.root, R.string.favourite_deleted_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        binding.backButton.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = binding.detinationDetailPhotosRecycler
        recyclerView.setHasFixedSize(true)
        manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = manager

        // Adapter
        destinationPhotos = intent.extras?.getStringArrayList("images")!!
        destinationDetailPhotoAdapter = DetinationDetailPhotoAdapter(destinationPhotos)
        recyclerView.adapter = destinationDetailPhotoAdapter
    }
}