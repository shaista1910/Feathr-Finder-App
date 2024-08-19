package com.example.feathrfindr

import com.google.android.gms.maps.model.LatLng

data class Hotspot(val name: String, val latitude: Double, val longitude: Double, val distance: Double, val location: LatLng) // (Google Maps Platform, 2023).

// Referencing List
// Google Maps Platform. 2023. Android maps sdk for android, 2023. [Online]. Available at: https://developers.google.com/maps/documentation/android-sdk/ktx [Accessed 17 September 2023].