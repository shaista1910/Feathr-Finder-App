package com.example.feathrfindr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.feathrfindr.R
import com.example.feathrfindr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSightings = view.findViewById<ImageView>(R.id.btnSightings)
        val btnChallenge = view.findViewById<ImageView>(R.id.btnChallenge)
        val btnBirdGuide = view.findViewById<ImageView>(R.id.btnBirdGuide)
        val btnBirdSound = view.findViewById<ImageView>(R.id.btnBirdSound)
        val btnHotspots = view.findViewById<ImageView>(R.id.btnHotspots)
        val btnSettings = view.findViewById<ImageView>(R.id.btnSettings)
        val btnGallery = view.findViewById<ImageView>(R.id.btnGallery)
        val btnInfo = view.findViewById<ImageView>(R.id.btnInfo)

        btnSightings.setOnClickListener {
            navigateToSightingsFragment()
        }

        btnChallenge.setOnClickListener {
            navigateToChallengeFragment()
        }

        btnBirdGuide.setOnClickListener {
            navigateToBirdGuideFragment()
        }
        btnBirdSound.setOnClickListener {
            navigateToBirdSoundFragment()
        }
        btnHotspots.setOnClickListener {
            navigateToHotspotsFragment()
        }
        btnSettings.setOnClickListener {
            navigateToSettingsFragment()
        }
        btnGallery.setOnClickListener {
            navigateToImageFragment()
        }
        btnInfo.setOnClickListener {
            navigateToInfoFragment()
        }
    }

    private fun navigateToSightingsFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_sightingsActivity)
    }

    private fun navigateToChallengeFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_challengeFragment)
    }

    private fun navigateToBirdGuideFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_birdGuideFragment)
    }
    private fun navigateToBirdSoundFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_birdSoundActivity)
    }
    private fun navigateToHotspotsFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_hotspotsActivity)
    }
    private fun navigateToSettingsFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_settingsActivity)
    }
    private fun navigateToImageFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_ImageActivity)
    }
    private fun navigateToInfoFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_InfoActivity)
    }


}



