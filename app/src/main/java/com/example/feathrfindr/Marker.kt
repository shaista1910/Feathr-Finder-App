package com.example.feathrfindr

data class Marker(val latitude: Double, val longitude: Double, val address: String) {
    // Add an empty constructor for Firebase
    constructor() : this(0.0, 0.0, "")
}
