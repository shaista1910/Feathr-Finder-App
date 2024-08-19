package com.example.feathrfindr

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    // values for the km / miles
    private lateinit var preferences: SharedPreferences
    private lateinit var unitRadioGroup: RadioGroup
    // values for the radius
    private lateinit var radiusSeekBar: SeekBar
    private lateinit var radiusValueText: TextView
    private lateinit var milesRadioButton: RadioButton
    private lateinit var kilometersRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize preferences in app
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Finding the SeekBar and TextView
        radiusSeekBar = findViewById(R.id.radiusSeekBar)
        radiusValueText = findViewById(R.id.radiusValueText)

        // Set up the SeekBar listener
        radiusSeekBar.max = 50 // The maximum radius allowed
        val savedRadius = preferences.getInt("radius_preference", 10)
        radiusSeekBar.progress = savedRadius
        radiusValueText.text = "$savedRadius km"

        radiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the displayed radius value
                radiusValueText.text = "$progress km"
                saveRadiusPreference(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Save the selected radius in shared preferences
                val selectedRadius = seekBar?.progress ?: 10 // Default radius
                preferences.edit().putInt("radius_preference", selectedRadius).apply()
            }
        })

        // Find the RadioGroup for unit selection and set a listener
        unitRadioGroup = findViewById(R.id.unit_radio_group)
        milesRadioButton = findViewById(R.id.radio_miles)
        kilometersRadioButton = findViewById(R.id.radio_km)

        val selectedUnit = preferences.getString("unit_preference", "km")
        when (selectedUnit) {
            "km" -> unitRadioGroup.check(R.id.radio_km)
            "miles" -> unitRadioGroup.check(R.id.radio_miles)
        }

        // Save the selected unit in shared preferences
        preferences.edit().putString("unit_preference", selectedUnit).apply()

        unitRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedUnit = when (checkedId) {
                R.id.radio_km -> "km"
                R.id.radio_miles -> "miles"
                else -> "km"
            }

            saveUnitPreference(selectedUnit)
            // Prevent both miles and kilometers from being selected
            if (milesRadioButton.isChecked && kilometersRadioButton.isChecked) {
                // Reset the selection to the default unit (e.g., kilometers).
                kilometersRadioButton.isChecked = true
                milesRadioButton.isChecked = false
                Toast.makeText(
                    this,
                    "Please select either Miles or Kilometers, not both.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun saveUnitPreference(selectedUnit: String) {
        preferences.edit().putString("unit_preference", selectedUnit).apply()
    }

    private fun saveRadiusPreference(radius: Int) {
        preferences.edit().putInt("radius_preference", radius).apply()
    }
}

// Referencing List
// W3schools. 2023. Kotlin Tutorial, 2023. [Online]. Avialable at: https://www.w3schools.com/kotlin/index.php [Accessed 17 September 2023]


