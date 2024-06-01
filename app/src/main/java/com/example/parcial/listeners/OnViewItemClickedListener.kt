package com.example.parcial.listeners

import com.example.parcial.entities.Flight


interface OnViewItemClickedListener {
    fun onViewItemDetail(flight: Flight)
}