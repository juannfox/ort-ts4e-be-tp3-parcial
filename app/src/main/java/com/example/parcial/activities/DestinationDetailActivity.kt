package com.example.parcial.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.parcial.R
import com.example.parcial.databinding.ActivityDestinationDetailBinding
import com.example.parcial.databinding.FragmentExploreBinding
import com.example.parcial.entities.Destination

class DestinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destinationItem = intent.getParcelableExtra<Destination>("destinationDetail")
        val destImg = intent.getStringExtra("mainImage")
        val images = intent.getStringArrayListExtra("images")

        Glide.with(this).load(destImg).centerCrop().into(binding.destinationBackgroundImage)
        binding.destinationName.text = destinationItem?.destinationName
        binding.destinationCity.text = destinationItem?.city
        binding.destinationPrice.text = "$${destinationItem?.price}"
        binding.destinationOverview.text = destinationItem?.overview
        binding.destinationDuration.text = destinationItem?.duration

        binding.destinationLikeButton.setOnCheckedChangeListener { checkBox, isChecked ->
            if(isChecked){
                Toast.makeText(this, "Destination ${destinationItem?.destinationName} liked", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Destination ${destinationItem?.destinationName} dislike", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}