package com.example.feathrfindr

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HotspotsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap // (Google Maps Platform, 2023).
    private lateinit var preferences: SharedPreferences // Declare preferences here
    private lateinit var userLocation: LatLng // (Ebird API 2.0, 2023)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotspots)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this) // (Ebird API 2.0, 2023)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "radius_preference") {
                val updatedRadius = sharedPreferences.getInt(key, 10).toDouble()
                val selectedUnit = sharedPreferences.getString("unit_preference", "km") ?: "km"

                // Clear existing markers and fetch and display hotspots with the updated radius
                googleMap.clear()
                fetchAndDisplayHotspots(updatedRadius, selectedUnit)
            }
        }  // (Ebird API 2.0, 2023)

         userLocation = LatLng(-26.0936, 28.0477) // (Google Maps Platform, 2023).
    }

    override fun onMapReady(map: GoogleMap) { // (Google Maps Platform, 2023).
        googleMap = map
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Handle permission requests
            return
        }// (Google Maps Platform, 2023).

        googleMap.isMyLocationEnabled = true

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))


        // Now that the map is ready, you can fetch and display hotspots
        val selectedUnit = preferences.getString("unit_preference", "km") ?: "km"
        val selectedRadius = preferences.getInt("radius_preference", 10).toDouble()

        // Clear existing markers
        googleMap.clear()// (Google Maps Platform, 2023).

        fetchAndDisplayHotspots(selectedRadius, selectedUnit)
    } // (Google Maps Platform, 2023).

    private fun fetchAndDisplayHotspots(radius: Double, unit: String) {    // (Ebird API 2.0, 2023)
        // Example hotspot data with latitude and longitude
        val hotspots = listOf(

            Hotspot("Kruger National Park", -23.988385, 31.554740, 5.0), // Kruger National Park coordinates, 5.0 kilometers
            Hotspot("Table Mountain", -33.9625, 18.4050, 2.5), // Table Mountain coordinates, 2.5 kilometers
            Hotspot("Addo Elephant National Park", -33.4833, 25.7500, 3.0), // Addo Elephant National Park coordinates, 3.0 kilometers
            Hotspot("Kirstenbosch Botanical Gardens", -33.9875, 18.4324, 1.0), // Kirstenbosch Botanical Gardens coordinates, 1.0 kilometers
            Hotspot("Blyde River Canyon Nature Reserve", -24.5717, 30.7841, 3.5), // Blyde River Canyon coordinates, 3.5 kilometers
            Hotspot("Amakhala Game Reserve", -33.4167, 26.0833, 2.0), // Amakhala Game Reserve coordinates, 2.0 kilometers
            Hotspot("Golden Gate Highlands National Park", -28.5924, 28.6847, 4.0), // Golden Gate Highlands National Park coordinates, 4.0 kilometers
            Hotspot("Hluhluwe-iMfolozi Park", -28.0304, 31.8999, 3.0), // Hluhluwe-iMfolozi Park coordinates, 3.0 kilometers
            Hotspot("Robben Island", -33.8052, 18.3581, 1.5), // Robben Island coordinates, 1.5 kilometers
            Hotspot("Augrabies Falls National Park", -28.5906, 20.3489, 2.5), // Augrabies Falls National Park coordinates, 2.5 kilometers
            Hotspot("Tsitsikamma National Park", -34.0134, 23.8975, 4.0), // Tsitsikamma National Park coordinates, 4.0 kilometers
            Hotspot("De Hoop Nature Reserve", -34.4351, 20.4426, 3.0), // De Hoop Nature Reserve coordinates, 3.0 kilometers
            Hotspot("Pilanesberg National Park", -25.2549, 27.0850, 2.5), // Pilanesberg National Park coordinates, 2.5 kilometers
            Hotspot("uKhahlamba-Drakensberg Park", -28.7321, 29.6000, 5.0), // uKhahlamba-Drakensberg Park coordinates, 5.0 kilometers
            Hotspot("Walter Sisulu National Botanical Garden", -26.1608, 27.8360, 2.0), // Walter Sisulu Botanical Garden coordinates, 2.0 kilometers
            Hotspot("Klipriviersberg Nature Reserve", -26.3059, 28.0667, 3.5), // Klipriviersberg Nature Reserve coordinates, 3.5 kilometers
            Hotspot("Emmarentia Dam and Botanical Gardens", -26.1435, 28.0018, 1.5), // Emmarentia Dam coordinates, 1.5 kilometers
            Hotspot("Melville Koppies Nature Reserve", -26.1795, 27.9995, 2.0), // Melville Koppies Nature Reserve coordinates, 2.0 kilometers
            Hotspot("Rietvlei Nature Reserve", -26.3326, 28.2516, 4.0), // Rietvlei Nature Reserve coordinates, 4.0 kilometers
            Hotspot("Lonehill Park", -26.0239, 28.0389, 1.0), // Lonehill Park coordinates, 1.0 kilometers
            Hotspot("Suikerbosrand Nature Reserve", -26.5076, 28.1461, 3.5), // Suikerbosrand Nature Reserve coordinates, 3.5 kilometers
            Hotspot("Johannesburg Botanical Garden", -26.1685, 28.0619, 2.0), // Johannesburg Botanical Garden coordinates, 2.0 kilometers
            Hotspot("Zoo Lake", -26.1567, 28.0319, 1.5), // Zoo Lake coordinates, 1.5 kilometers
            Hotspot("Delta Park", -26.1307, 28.0235, 2.0), // Delta Park coordinates, 2.0 kilometers
            Hotspot("Lonehill Park", -26.0239, 28.0389, 1.0), // Lonehill Park coordinates, 1.0 kilometers
            Hotspot("Suikerbosrand Nature Reserve", -26.5076, 28.1461, 3.5), // Suikerbosrand Nature Reserve coordinates, 3.5 kilometers
            Hotspot("Johannesburg Botanical Garden", -26.1685, 28.0619, 2.0), // Johannesburg Botanical Garden coordinates, 2.0 kilometers
            Hotspot("Zoo Lake", -26.1567, 28.0319, 1.5), // Zoo Lake coordinates, 1.5 kilometers
            Hotspot("Delta Park", -26.1307, 28.0235, 2.0), // Delta Park coordinates, 2.0 kilometers
            Hotspot("Modderfontein Nature Reserve", -26.1177, 28.1478, 1.5), // Modderfontein Nature Reserve coordinates, 1.5 kilometers
            Hotspot("Rietfontein Nature Reserve", -26.0516, 28.0963, 2.0), // Rietfontein Nature Reserve coordinates, 2.0 kilometers
            Hotspot("Walter Pearson Park", -26.1451, 28.0622, 1.0), // Walter Pearson Park coordinates, 1.0 kilometers
            Hotspot("Jan Smuts House Museum", -26.1584, 28.0519, 1.2), // Jan Smuts House Museum coordinates, 1.2 kilometers
            Hotspot("Emmarentia Ridge", -26.1495, 28.0169, 1.8), // Emmarentia Ridge coordinates, 1.8 kilometers
            Hotspot("George Hay Park", -26.2210, 28.1080, 1.3), // George Hay Park coordinates, 1.3 kilometers
            Hotspot("Korsman Bird Sanctuary", -26.3204, 28.0071, 1.5), // Korsman Bird Sanctuary coordinates, 1.5 kilometers
            Hotspot("Huddle Park", -26.1977, 28.1411, 1.2), // Huddle Park coordinates, 1.2 kilometers
            Hotspot("Zebra Country Lodge", -25.8278, 28.6253, 3.5), // Zebra Country Lodge coordinates, 3.5 kilometers
            Hotspot("Melrose Bird Sanctuary", -26.1336, 28.0591, 1.0), // Melrose Bird Sanctuary coordinates, 1.0 kilometers
            Hotspot("Sandton Central Park", -26.1043, 28.0522, 1.5), // Sandton Central Park coordinates, 1.5 kilometers
            Hotspot("Benmore Botanical Gardens", -26.1024, 28.0553, 1.0), // Benmore Botanical Gardens coordinates, 1.0 kilometers
            Hotspot("Morningside Nature Reserve", -26.0838, 28.0667, 1.8), // Morningside Nature Reserve coordinates, 1.8 kilometers
            Hotspot("Wendywood Wetland", -26.0867, 28.1004, 1.2), // Wendywood Wetland coordinates, 1.2 kilometers
            Hotspot("Kyalami Equestrian Park", -25.9842, 28.1054, 2.0), // Kyalami Equestrian Park coordinates, 2.0 kilometers
            Hotspot("Rivonia Wetland", -26.0556, 28.0672, 1.3), // Rivonia Wetland coordinates, 1.3 kilometers
            Hotspot("Bryanston Country Club", -26.0439, 28.0298, 2.0), // Bryanston Country Club coordinates, 2.0 kilometers
            Hotspot("Grayston Park", -26.0987, 28.0545, 1.0), // Grayston Park coordinates, 1.0 kilometers
            Hotspot("Hurlingham Park", -26.1028, 28.0312, 1.5), // Hurlingham Park coordinates, 1.5 kilometers
            Hotspot("Parkmore Bird Sanctuary", -26.1004, 28.0385, 0.8), // Parkmore Bird Sanctuary coordinates, 0.8 kilometers
            Hotspot("Innesfree Park", -26.0467, 28.0596, 1.2), // Innesfree Park coordinates, 1.2 kilometers
            Hotspot("Paulshof Park", -26.0355, 28.0634, 1.0), // Paulshof Park coordinates, 1.0 kilometers
            Hotspot("Leeukop Golf Course", -26.0392, 28.0623, 2.5), // Leeukop Golf Course coordinates, 2.5 kilometers
            Hotspot("Wierda Valley Park", -26.0963, 28.0397, 1.2), // Wierda Valley Park coordinates, 1.2 kilometers
            Hotspot("Norscot Koppies Nature Reserve", -26.0047, 28.0191, 3.0), // Norscot Koppies Nature Reserve coordinates, 3.0 kilometers
            Hotspot("Melrose Arch", -26.1358, 28.0677, 0.8), // Melrose Arch coordinates, 0.8 kilometers
            Hotspot("Mushroom Farm Park", -26.0786, 28.0737, 1.5), // Mushroom Farm Park coordinates, 1.5 kilometers
            Hotspot("Randburg Waterfront", -26.0936, 27.9932, 2.2), // Randburg Waterfront coordinates, 2.2 kilometers
            Hotspot("Jackal Creek Golf Estate", -26.0645, 27.9497, 3.5), // Jackal Creek Golf Estate coordinates, 3.5 kilometers
            Hotspot("Fourways Golf Park", -26.0143, 28.0088, 2.0), // Fourways Golf Park coordinates, 2.0 kilometers
            Hotspot("Lonehill Park", -26.0113, 28.0144, 1.0), // Lonehill Park coordinates, 1.0 kilometers
            Hotspot("Beverley Hills Bird Sanctuary", -26.0309, 28.0043, 1.8), // Beverley Hills Bird Sanctuary coordinates, 1.8 kilometers
            Hotspot("Woodmead Country Club", -26.0526, 28.0457, 2.2), // Woodmead Country Club coordinates, 2.2 kilometers
            Hotspot("Gallo Manor Bird Sanctuary", -26.0661, 28.0691, 1.5), // Gallo Manor Bird Sanctuary coordinates, 1.5 kilometers
            Hotspot("Morningside Bird Sanctuary", -26.0830, 28.0651, 1.2), // Morningside Bird Sanctuary coordinates, 1.2 kilometers
            Hotspot("Kogelberg Nature Reserve", -34.3300, 19.0160, 2.5),
            Hotspot("Witvoetskloof Nature Reserve", -32.9386, 22.3344, 3.0),
            Hotspot("Anysberg Nature Reserve", -33.4439, 21.5547, 2.2),
            Hotspot("Bontebok National Park", -34.0525, 20.4396, 1.5),
            Hotspot("De Hoop Nature Reserve", -34.4253, 20.4424, 3.5),
            Hotspot("Cape Point", -34.3564, 18.4828, 2.0),
            Hotspot("Robben Island Bird Sanctuary", -33.8061, 18.3610, 1.8),
            Hotspot("Hottentots Holland Nature Reserve", -34.1055, 18.9252, 3.0),
            Hotspot("Tankwa Karoo National Park", -32.3159, 19.6556, 4.0),
            Hotspot("Karoo National Park", -32.2473, 22.5820, 2.5),
            Hotspot("Swartberg Nature Reserve", -33.3162, 22.3099, 2.0),
            Hotspot("Meiringspoort", -33.3939, 22.4132, 1.5),
            Hotspot("Gamkaskloof (Die Hel)", -33.3842, 22.1926, 2.5),
            Hotspot("Augrabies Falls National Park", -28.5918, 20.3426, 3.2),
            Hotspot("Kalahari Gemsbok National Park", -26.6208, 20.4367, 4.0),
            Hotspot("Richtersveld Transfrontier Park", -28.6555, 17.0640, 5.0),
            Hotspot("Goegap Nature Reserve", -29.7317, 17.8512, 2.5),
            Hotspot("Nieuwoudtville Wildflower Reserve", -31.3893, 19.1207, 1.8),
            Hotspot("Tankwa Karoo National Park", -32.3012, 19.6598, 4.2),
            Hotspot("Aloes on the Klipkolk", -29.1859, 19.7530, 1.2),
            Hotspot("Helderberg Nature Reserve", -34.0722, 18.8847, 2.0),
            Hotspot("Rondevlei Nature Reserve", -34.0325, 18.5390, 1.5),
            Hotspot("Harold Porter Botanical Garden", -34.2554, 18.8999, 1.8),
            Hotspot("Verlorenvlei Nature Reserve", -32.3617, 18.2304, 3.0),
            Hotspot("West Coast National Park", -32.9051, 18.0733, 4.0),
            Hotspot("Robben Island Nature Reserve", -33.8061, 18.3610, 2.5),
            Hotspot("Cederberg Wilderness Area", -32.3949, 19.3180, 4.5),
            Hotspot("Groot Winterhoek Wilderness Area", -33.2406, 19.3140, 4.2),
            Hotspot("Kammanassie Nature Reserve", -33.5122, 23.1286, 3.8),
            Hotspot("Gamkaberg Nature Reserve", -33.6822, 21.7747, 3.5),
            Hotspot("Theewaterskloof Dam", -34.0784, 19.1683, 2.0),
            Hotspot("Paarl Bird Sanctuary", -33.7493, 18.9683, 1.0),
            Hotspot("Klipheuwel Bird Sanctuary", -33.7128, 18.8184, 0.8),
            Hotspot("Zeekoevlei Bird Sanctuary", -34.0421, 18.5137, 1.2),
            Hotspot("Strandfontein Birding Area", -34.0721, 18.5687, 1.5),
            Hotspot("False Bay Nature Reserve", -34.0949, 18.5116, 2.0),
            Hotspot("Durbanville Nature Reserve", -33.8475, 18.6202, 1.5),
            Hotspot("Jonkershoek Nature Reserve", -33.9883, 18.9560, 2.2),
            Hotspot("Simonsberg Conservancy", -33.9515, 18.8962, 1.8),
            Hotspot("Jonkershoek Valley", -33.9891, 18.9194, 2.5),
            Hotspot("Zandvlei Bird Sanctuary", -34.0806, 18.4854, 1.5),
            Hotspot("Intaka Island", -33.8952, 18.5237, 1.8),
            Hotspot("Rondebosch Common", -33.9582, 18.4635, 1.2),
            Hotspot("Silvermine Nature Reserve", -34.0753, 18.4196, 2.5),
            Hotspot("Boulders Beach", -34.1967, 18.4554, 1.5),
            Hotspot("Constantia Greenbelt", -34.0367, 18.4496, 2.0),
            Hotspot("Kenilworth Racecourse Conservation Area", -33.9983, 18.4659, 1.0),
            Hotspot("Zevenwacht Wine Estate", -33.9669, 18.7061, 1.8),
            Hotspot("Table Bay Nature Reserve", -33.8396, 18.4691, 1.2),
            Hotspot("Tygerberg Nature Reserve", -33.8667, 18.6460, 2.0),
            Hotspot("Groot Constantia Wine Estate", -34.0293, 18.4252, 1.5),
            Hotspot("Newlands Forest", -33.9872, 18.4499, 1.5),
            Hotspot("Kirstenbosch Gardens", -33.9882, 18.4324, 2.2),
            Hotspot("Tokai Forest", -34.0622, 18.4263, 1.8),
            Hotspot("Rondevlei Nature Reserve", -34.0325, 18.5390, 1.2),
            Hotspot("Helderberg Nature Reserve", -34.0722, 18.8847, 2.5),
            Hotspot("Noordhoek Common", -34.0937, 18.3787, 1.0),
            Hotspot("Kommetjie Environmental Awareness Group", -34.1307, 18.3286, 1.2),
            Hotspot("Koeberg Nature Reserve", -33.6557, 18.4243, 1.5),
            Hotspot("Strandfontein Birding Area", -34.0721, 18.5687, 1.8),
            Hotspot("False Bay Nature Reserve", -34.0949, 18.5116, 2.0),
            Hotspot("Cape Point", -34.3564, 18.4828, 2.2),
            Hotspot("Harold Porter Botanical Garden", -34.2554, 18.8999, 2.0),
            Hotspot("Simonsberg Conservancy", -33.9515, 18.8962, 1.8),
            Hotspot("De Hoop Nature Reserve", -34.4253, 20.4424, 3.5),
            Hotspot("Robben Island Bird Sanctuary", -33.8061, 18.3610, 2.5),
            Hotspot("Gamkaberg Nature Reserve", -33.6822, 21.7747, 3.0),
            Hotspot("Karoo National Park", -32.2473, 22.5820, 2.5),
            Hotspot("Swartberg Nature Reserve", -33.3162, 22.3099, 2.0),
            Hotspot("Meiringspoort", -33.3939, 22.4132, 1.5),
            Hotspot("Gamkaskloof (Die Hel)", -33.3842, 22.1926, 2.5),
            Hotspot("Augrabies Falls National Park", -28.5918, 20.3426, 3.2),
            Hotspot("Kalahari Gemsbok National Park", -26.6208, 20.4367, 4.0),
            Hotspot("Richtersveld Transfrontier Park", -28.6555, 17.0640, 5.0),
            Hotspot("Goegap Nature Reserve", -29.7317, 17.8512, 2.5),
            Hotspot("Nieuwoudtville Wildflower Reserve", -31.3893, 19.1207, 1.8),
            Hotspot("Tankwa Karoo National Park", -32.3012, 19.6598, 4.2),
            Hotspot("Aloes on the Klipkolk", -29.1859, 19.7530, 1.2),
            Hotspot("Umhlanga Lagoon Nature Reserve", -29.7233, 31.1010, 1.5),
            Hotspot("Kenneth Stainbank Nature Reserve", -29.9494, 30.9522, 1.8),
            Hotspot("Isipingo Mangrove Swamps", -30.0553, 30.8989, 1.2),
            Hotspot("Durban Botanic Gardens", -29.8533, 31.0160, 2.5),
            Hotspot("Paradise Valley Nature Reserve", -29.8558, 30.9325, 1.5),
            Hotspot("Japanese Gardens, Durban", -29.8673, 30.9885, 2.0),
            Hotspot("Umgeni River Bird Park", -29.8280, 31.0002, 1.0),
            Hotspot("Palmiet Nature Reserve", -30.8656, 30.3681, 1.8),
            Hotspot("Vernon Crookes Nature Reserve", -30.2213, 30.6930, 1.5),
            Hotspot("Kranti Bird Sanctuary", -29.7632, 30.7560, 2.0),
            Hotspot("Umgeni River Estuary", -29.7739, 31.0368, 1.5),
            Hotspot("Dolphins View, Amanzimtoti", -30.0484, 30.8685, 1.8),
            Hotspot("Tala Game Reserve", -29.7719, 30.5275, 2.2),
            Hotspot("Pigeon Valley Nature Reserve", -29.8506, 31.0005, 1.5),
            Hotspot("Westville Palmiet Nature Reserve", -29.8309, 30.9316, 1.8),
            Hotspot("Durban Green Corridor", -29.8789, 30.9550, 2.0),
            Hotspot("Albert Falls Nature Reserve", -29.4267, 30.3445, 1.5),
            Hotspot("Shongweni Dam and Nature Reserve", -29.7574, 30.7320, 2.5),
            Hotspot("Bluff Nature Reserve", -30.9634, 30.9927, 2.0),
            Hotspot("Durban North Bird Sanctuary", -29.7800, 31.0359, 1.5),
            Hotspot("Ongoye Forest Reserve", -28.6717, 31.5542, 2.2),
            Hotspot("Umhlanga Nature Reserve", -29.7278, 31.1055, 1.8),
            Hotspot("Mount Moreland Swallows", -29.7076, 31.0786, 1.2),
            Hotspot("Dlinza Forest, Eshowe", -28.8925, 31.4650, 2.5),
            Hotspot("Mkhuze Game Reserve", -27.6344, 32.0311, 3.0),
            Hotspot("Umlalazi Nature Reserve", -28.9095, 31.7816, 1.5),
            Hotspot("Mun-Ya-Wana Game Reserve", -28.7139, 31.6763, 2.0),
            Hotspot("Weenen Game Reserve", -28.7012, 29.9782, 1.8),
            Hotspot("Albert Falls Dam", -29.4055, 30.2149, 1.2),
            Hotspot("Midmar Dam", -29.4550, 30.1335, 2.0),
            Hotspot("Howick Falls", -29.4734, 30.2319, 1.5),
            Hotspot("Dargle Valley Birding Route", -29.2196, 30.1005, 2.5),
            Hotspot("Ntshondwe Camp, Ithala Game Reserve", -27.3900, 31.4986, 3.2),
            Hotspot("Pongola Game Reserve", -27.3820, 31.8193, 4.0),
            Hotspot("Tembe Elephant Park", -27.0299, 32.2645, 3.5),
            Hotspot("Ndumo Game Reserve", -26.8556, 32.2922, 3.0),
            Hotspot("iSimangaliso Wetland Park - St. Lucia", -28.3832, 32.4185, 4.2),
            Hotspot("Umkhumbi Lodge, Mkhuze", -27.6492, 32.0275, 1.8),
            Hotspot("Bonamanzi Game Reserve", -27.4514, 32.1159, 2.2),
            Hotspot("Amatikulu Nature Reserve", -29.1532, 31.5169, 2.5),
            Hotspot("Satara Rest Camp, Kruger National Park", -24.3863, 31.7945, 1.5),
            Hotspot("Skukuza Rest Camp, Kruger National Park", -24.9958, 31.5959, 1.8),
            Hotspot("Lower Sabie Rest Camp, Kruger National Park", -25.1119, 31.9479, 1.2),
            Hotspot("Mopani Rest Camp, Kruger National Park", -23.6744, 31.0913, 2.5),
            Hotspot("Punda Maria Rest Camp, Kruger National Park", -22.6799, 31.4224, 1.5),
            Hotspot("Olifants Rest Camp, Kruger National Park", -23.9367, 31.1856, 2.0),
            Hotspot("Balule Rest Camp, Kruger National Park", -24.1286, 30.9053, 1.0),
            Hotspot("Shingwedzi Rest Camp, Kruger National Park", -22.4827, 31.2044, 1.8),
            Hotspot("Bateleur Bushveld Camp, Kruger National Park", -25.2415, 31.6483, 1.5),
            Hotspot("Biyamiti Bushveld Camp, Kruger National Park", -25.0193, 31.6166, 2.2),
            Hotspot("Sirheni Bushveld Camp, Kruger National Park", -23.8932, 31.3605, 1.8),
            Hotspot("Talamati Bushveld Camp, Kruger National Park", -24.2955, 31.8023, 2.0),
            Hotspot("Tamboti Tented Camp, Kruger National Park", -24.1333, 31.5300, 1.5),
            Hotspot("Maroela Camp, Kruger National Park", -24.9467, 31.4700, 1.5),
            Hotspot("Orpen Rest Camp, Kruger National Park", -24.4484, 31.1926, 2.0),
            Hotspot("Letaba Rest Camp, Kruger National Park", -23.7526, 31.5707, 1.8),
            Hotspot("Mata-Mata Rest Camp, Kruger National Park", -27.5562, 20.4723, 2.5),
            Hotspot("Shimuwini Bushveld Camp, Kruger National Park", -23.9641, 31.3847, 1.2),
            Hotspot("Tsendze Rustic Camp, Kruger National Park", -22.5667, 31.3496, 1.5),
            Hotspot("Pafuri Border Camp, Kruger National Park", -22.4842, 31.0158, 2.0),
            Hotspot("Hwange National Park, Zimbabwe", -18.6070, 26.7853, 3.0),
            Hotspot("Matobo National Park, Zimbabwe", -20.4817, 28.5475, 2.5),
            Hotspot("Mana Pools National Park, Zimbabwe", -15.8074, 29.2994, 2.0),
            Hotspot("Victoria Falls, Zimbabwe", -17.9226, 25.8557, 1.5),
            Hotspot("Eastern Highlands, Zimbabwe", -18.9977, 32.6210, 1.8),
            Hotspot("Lake Kariba, Zimbabwe", -16.5313, 28.8046, 2.0),
            Hotspot("Chizarira National Park, Zimbabwe", -17.8533, 27.7337, 2.2),
            Hotspot("Gonarezhou National Park, Zimbabwe", -21.1900, 31.6381, 2.0),
            Hotspot("Matusadona National Park, Zimbabwe", -16.4292, 28.6782, 1.8),
            Hotspot("Nyanga National Park, Zimbabwe", -18.1790, 32.7168, 1.2),
            Hotspot("Binga, Lake Kariba, Zimbabwe", -17.3064, 27.4477, 2.5),
            Hotspot("Chinhoyi Caves, Zimbabwe", -17.3572, 30.1799, 1.5),
            Hotspot("Kuimba Shiri Bird Sanctuary, Zimbabwe", -17.8877, 30.9512, 1.8),
            Hotspot("Harare Botanic Gardens, Zimbabwe", -17.7312, 31.0513, 1.5),
            Hotspot("Harare Kopje, Zimbabwe", -17.8270, 31.0547, 2.0),
            Hotspot("Gweru Bird Park, Zimbabwe", -19.4373, 29.8065, 1.5),
            Hotspot("Antelope Park, Zimbabwe", -19.6156, 30.2600, 2.0),
            Hotspot("Lake Chivero, Zimbabwe", -17.8922, 30.9978, 1.8),
            Hotspot("Mukuvisi Woodlands, Zimbabwe", -17.8576, 31.0662, 1.2),
            Hotspot("Thetford Game Reserve, Zimbabwe", -17.5300, 31.2422, 2.5),
            Hotspot("Okavango Delta, Botswana", -19.3293, 22.8438, 3.0),
            Hotspot("Chobe National Park, Botswana", -18.9020, 24.5723, 2.5),
            Hotspot("Moremi Game Reserve, Botswana", -19.2290, 23.4608, 2.2),
            Hotspot("Makgadikgadi Pans, Botswana", -20.4775, 25.7193, 1.5),
            Hotspot("Central Kalahari Game Reserve, Botswana", -21.3215, 24.8131, 1.8),
            Hotspot("Khwai Community Concession, Botswana", -19.1843, 23.5726, 2.0),
            Hotspot("Nxai Pan National Park, Botswana", -20.2237, 24.7555, 1.5),
            Hotspot("Linyanti Wildlife Reserve, Botswana", -18.3313, 23.9853, 1.0),
            Hotspot("Tuli Block, Botswana", -22.2276, 29.3442, 1.8),
            Hotspot("Mabuasehube Game Reserve, Botswana", -24.2232, 20.5256, 2.0),
            Hotspot("Khama Rhino Sanctuary, Botswana", -22.3095, 27.2045, 1.5),
            Hotspot("Okavango Panhandle, Botswana", -18.1180, 21.7476, 2.5),
            Hotspot("Gaborone Game Reserve, Botswana", -24.6713, 25.8390, 1.5),
            Hotspot("Kasane Forest Reserve, Botswana", -17.8092, 25.1415, 1.8),
            Hotspot("Savute Marsh, Botswana", -18.5142, 24.1904, 1.5),
            Hotspot("Mashatu Game Reserve, Botswana", -22.1733, 29.2624, 2.0),
            Hotspot("Xakanaxa Camp, Botswana", -19.3349, 23.2031, 1.2),
            Hotspot("Moremi Tongue, Botswana", -19.4799, 23.2665, 2.5),
            Hotspot("Kalahari Plains Camp, Botswana", -21.4962, 23.7513, 1.8),
            Hotspot("Deception Valley, Botswana", -21.8419, 24.6744, 2.2),
            Hotspot("Your Location", -26.0936, 28.0477, 2.2),
            // (Ebird API 2.0, 2023)
            // (Ebird API 2.0, 2023)
        )// (Ebird API 2.0, 2023)

        for (hotspot in hotspots) {
            val distance = calculateDistance(userLocation, LatLng(hotspot.latitude, hotspot.longitude))
            val convertedDistance = convertDistance(distance, "km", unit)

            if (convertedDistance <= radius) {
                val formattedDistance = "$convertedDistance $unit"

                val markerOptions = MarkerOptions()
                    .position(LatLng(hotspot.latitude, hotspot.longitude))
                    .title("${hotspot.name} - $formattedDistance")
                googleMap.addMarker(markerOptions)
            }
        }// (Google Maps Platform, 2023).
    }

    private fun calculateDistance(location1: LatLng, location2: LatLng): Double {
        val lat1 = location1.latitude
        val lon1 = location1.longitude
        val lat2 = location2.latitude
        val lon2 = location2.longitude

        val R = 6371 // Earth radius in kilometers
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }// (Google Maps Platform, 2023).

    private fun convertKilometersToMiles(kilometers: Double): Double {
        return kilometers * 0.621371
    }

    // Function to convert distances between units
    private fun convertDistance(distance: Double, fromUnit: String, toUnit: String): Double {
        return when {
            fromUnit == "km" && toUnit == "miles" -> convertKilometersToMiles(distance)
            fromUnit == "miles" && toUnit == "km" -> distance / 0.621371
            else -> distance
        }// (Google Maps Platform, 2023).
    }
    data class Hotspot(val name: String, val latitude: Double, val longitude: Double, val distance: Double)
}
// Referencing List
// Google Maps Platform. 2023. Android maps sdk for android, 2023. [Online]. Available at: https://developers.google.com/maps/documentation/android-sdk/ktx [Accessed 17 September 2023].
// Ebird API 2.0. 2023. Ebird postman documentation, 2023. [Online]. Available at: https://documenter.getpostman.com/view/664302/S1ENwy59#4e020bc2-fc67-4fb6-a926-570cedefcc34 [Accessed 17 September 2023].