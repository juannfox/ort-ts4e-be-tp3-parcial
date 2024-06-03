package com.example.parcial.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parcial.R
import com.example.parcial.adapters.DestinationAdapter
import com.example.parcial.adapters.DetinationDetailPhotoAdapter
import com.example.parcial.databinding.ActivityDestinationDetailBinding
import com.example.parcial.databinding.FragmentExploreBinding
import com.example.parcial.domain.FavouriteUseCase
import com.example.parcial.entities.Destination
import com.example.parcial.entities.Favourite
import com.example.parcial.entities.FavouriteType
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding

    lateinit var recyclerView: RecyclerView
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var destinationDetailPhotoAdapter: RecyclerView.Adapter<*>
    private lateinit var destinationPhotos: MutableList<String>

    @Inject
    lateinit var favouriteUseCase: FavouriteUseCase

    val isSaved = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val isFavourite = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var viewModel = ViewModelProvider(this).get(DestinationDetailViewModel::class.java);
        binding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destinationItem = intent.getParcelableExtra<Destination>("destinationDetail")
        val destImg = intent.getStringExtra("mainImage")

        isChecked("${destinationItem?.destinationName}-${destinationItem?.city}")

        Glide.with(this).load(destImg).centerCrop().into(binding.destinationBackgroundImage)
        binding.destinationName.text = destinationItem?.destinationName
        binding.destinationCity.text = destinationItem?.city
        binding.destinationPrice.text = "$${destinationItem?.price}"
        binding.destinationOverview.text = destinationItem?.overview
        binding.destinationDuration.text = destinationItem?.duration

        binding.destinationLikeButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                //Se está activando cuando verifico al inicio se se está  - ver como evitarlo cuando abro la pantalla tal vez hacer esta validación en otro momento de lifecycle
                addFavourite("${destinationItem?.destinationName}-${destinationItem?.city}")
            } else {
                removeFavourite("${destinationItem?.destinationName}-${destinationItem?.city}")
                //Toast.makeText(this, "Destination ${destinationItem?.destinationName} dislike", Toast.LENGTH_SHORT).show()
            }
        }

        isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        isFavourite.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                binding.destinationLikeButton.isChecked = true
            }
        })

        isSaved.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
                Snackbar.make(binding.root, R.string.favourite_saved_alert, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.favourite_error_alert, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        isDeleted.observe(this, Observer { isSavedValue ->
            if (isSavedValue) {
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
    }

    override fun onStart() {
        super.onStart()
        recyclerView = binding.detinationDetailPhotosRecycler
        recyclerView.setHasFixedSize(true)
        manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = manager

        // Adapter
        destinationPhotos = intent.extras?.getStringArrayList("images")!!
        Log.d("PARCIAL-FOTOSS", destinationPhotos.size.toString())
        destinationDetailPhotoAdapter = DetinationDetailPhotoAdapter(destinationPhotos)
        recyclerView.adapter = destinationDetailPhotoAdapter
    }

    private fun addFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.saveFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isSaved.postValue(result != -1L)
            }

        }
    }

    private fun removeFavourite(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            var result =
                favouriteUseCase.removeFavourite(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isDeleted.postValue(result != -1)
            }
        }
    }

    private fun isChecked(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.postValue(true)
            var result = favouriteUseCase.exists(Favourite(FavouriteType.DESTINATION.type, id))

            if (result != null) {
                isFavourite.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}