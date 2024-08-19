package com.example.feathrfindr


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*



class MarkBirdLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    private var newLatLng: LatLng? = null
    private var address: String = ""

    private lateinit var database: DatabaseReference

    fun centerMapOnLocation(
        location: Location?,
        title: String?
    ) {
        if (intent.getStringExtra("latLng").isNullOrEmpty()) {
            if (location != null) {
                val userLocation = LatLng(location.latitude, location.longitude)
                addMarker(userLocation, title ?: "Your location")
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 25f))
            }
        } else {
            val latLngArray = intent.getStringExtra("latLng")?.split(" ", ",")

            val latitude = latLngArray?.getOrNull(1)?.substring(1)?.toDoubleOrNull() ?: 0.0
            val longitude = latLngArray?.getOrNull(2)?.substring(0, latLngArray[2].length - 1)?.toDoubleOrNull()
                ?: 0.0

            val location = LatLng(latitude, longitude)
            addMarker(location, intent.getStringExtra("address") ?: "Marker")
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 25f))
        }
    }

    private fun addMarker(latLng: LatLng, title: String) {
        mMap.addMarker(MarkerOptions().position(latLng).title(title))
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Marker Name")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            val markerName = input.text.toString()
            if (markerName.isNotEmpty()) {
                address = markerName
                newLatLng?.let { addMarker(it, markerName) }
                // Save the marker to Firebase
                saveMarkerToFirebase(newLatLng, markerName)
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }


    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            showInputDialog()

            val geocoder = Geocoder(applicationContext, Locale.getDefault())
            address = ""
            try {
                val listAddresses: List<Address> =
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1) as List<Address>

                if (listAddresses != null && listAddresses.isNotEmpty()) {
                    if (listAddresses[0].thoroughfare != null) {
                        if (listAddresses[0].subThoroughfare != null) {
                            address += listAddresses[0].subThoroughfare + " "
                        }
                        address += listAddresses[0].thoroughfare
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (address == "") {
                val sdf = SimpleDateFormat(" HH:mm dd-MM-yyyy")
                address += sdf.format(Date())
            }

            map.clear()
            map.addMarker(MarkerOptions().position(latLng).title(address))
            newLatLng = latLng
        }
    }

    private fun saveMarkerToFirebase(latLng: LatLng?, address: String) {
        // Create a unique key for the marker
        val markerKey = database.child("markers").push().key

        // Create a Marker object with latitude, longitude, and address
        val marker = Marker(latLng?.latitude ?: 0.0, latLng?.longitude ?: 0.0, address)

        // Save the marker to the "markers" node in Firebase
        database.child("markers").child(markerKey ?: "").setValue(marker)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    locationListener?.let {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            0,
                            0f,
                            it
                        )
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            if (newLatLng != null) {
                val resultIntent = Intent(this, DetailsActivity::class.java)
                resultIntent.putExtra("latLng", newLatLng.toString())
                resultIntent.putExtra("address", address)

                // Save marker information to Firebase
                saveMarkerToFirebase(newLatLng, address)

                Log.i("LATLNG", "onOptionsItemSelected: " + address)
                Log.i("LATLNG", "onOptionsItemSelected: " + newLatLng.toString())
                setResult(RESULT_OK, resultIntent)
            }
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_bird_location)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("markers")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener =
            object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    centerMapOnLocation(location, "Your location.")
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0, 10f, locationListener as LocationListener
            )
            val lastKnownLocation =
                locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            centerMapOnLocation(lastKnownLocation, "Your Location")
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        setMapLongClick(mMap)
    }
}










