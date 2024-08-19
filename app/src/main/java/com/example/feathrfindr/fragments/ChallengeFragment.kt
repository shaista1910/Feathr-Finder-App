package com.example.feathrfindr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.feathrfindr.R

class ChallengeFragment : Fragment() {

    private lateinit var rollButton: Button
    private lateinit var diceImage: ImageView

    inner class Dice(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_challenge, container, false)

        rollButton = view.findViewById(R.id.button)
        diceImage = view.findViewById(R.id.imageView)

        rollButton.setOnClickListener { rollDice() }

        // Roll a dice when the fragment is created. This is because otherwise it would be an empty page.
        rollDice()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // Roll a dice and set the drawable image to the view
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()

        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()
    }
}



