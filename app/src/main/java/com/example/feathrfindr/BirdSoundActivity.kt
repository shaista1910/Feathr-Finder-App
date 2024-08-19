package com.example.feathrfindr

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")

class BirdSoundActivity : AppCompatActivity() {

    private var soundPool: SoundPool? = null
    private val soundId = 1

    private var soundPool2: SoundPool? = null
    private val soundId2 = 2

    private var soundPool3: SoundPool? = null
    private val soundId3 = 3

    private var soundPool4: SoundPool? = null
    private val soundId4 = 4

    private var soundPool5: SoundPool? = null
    private val soundId5 = 5

    private var soundPool6: SoundPool? = null
    private val soundId6 = 6

    private var soundPool7: SoundPool? = null
    private val soundId7 = 7

    private var soundPool8: SoundPool? = null
    private val soundId8 = 8

    private var soundPool9: SoundPool? = null
    private val soundId9 = 9

    private var soundPool10: SoundPool? = null
    private val soundId10 = 10

    private var soundPool11: SoundPool? = null
    private val soundId11 = 11

    private var soundPool12: SoundPool? = null
    private val soundId12 = 12

    private var soundPool13: SoundPool? = null
    private val soundId13 = 13

    private var soundPool14: SoundPool? = null
    private val soundId14 = 14

    private var soundPool15: SoundPool? = null
    private val soundId15 = 15

    private var soundPool16: SoundPool? = null
    private val soundId16 = 16

    private var soundPool17: SoundPool? = null
    private val soundId17 = 17

    private var soundPool18: SoundPool? = null
    private val soundId18 = 18



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_sound)

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.load(baseContext, R.raw.recording, 1)

        soundPool2 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool2!!.load(baseContext, R.raw.recording2, 1)

        soundPool3 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool3!!.load(baseContext, R.raw.recording3, 1)

        soundPool4 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool4!!.load(baseContext, R.raw.recording4, 1)

        soundPool5 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool5!!.load(baseContext, R.raw.recording5, 1)

        soundPool6 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool6!!.load(baseContext, R.raw.recording6, 1)

        soundPool7 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool7!!.load(baseContext, R.raw.recording7, 1)

        soundPool8 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool8!!.load(baseContext, R.raw.recording8, 1)

        soundPool9 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool9!!.load(baseContext, R.raw.recording9, 1)

        soundPool10 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool10!!.load(baseContext, R.raw.recording10, 1)

        soundPool11 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool11!!.load(baseContext, R.raw.recording11, 1)

        soundPool12 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool12!!.load(baseContext, R.raw.recording12, 1)

        soundPool13 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool13!!.load(baseContext, R.raw.recording13, 1)

        soundPool14 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool3!!.load(baseContext, R.raw.recording14, 1)

        soundPool15 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool15!!.load(baseContext, R.raw.recording15, 1)

        soundPool16 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool16!!.load(baseContext, R.raw.recording16, 1)

        soundPool17 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool17!!.load(baseContext, R.raw.recording17, 1)

        soundPool18 = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool18!!.load(baseContext, R.raw.recording18, 1)
    }

    fun playSound(view: View) {
        soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }
    fun playSound2(view: View) {
        soundPool2?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound3(view: View) {
        soundPool3?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound4(view: View) {
        soundPool4?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound5(view: View) {
        soundPool5?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound6(view: View) {
        soundPool6?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound7(view: View) {
        soundPool7?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound8(view: View) {
        soundPool8?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }


    fun playSound9(view: View) {
        soundPool9?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound10(view: View) {
        soundPool10?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound11(view: View) {
        soundPool11?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound12(view: View) {
        soundPool12?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }
    fun playSound13(view: View) {
        soundPool13?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }
    fun playSound14(view: View) {
        soundPool14?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }
    fun playSound15(view: View) {
        soundPool15?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound16(view: View) {
        soundPool16?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound17(view: View) {
        soundPool17?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

    fun playSound18(view: View) {
        soundPool18?.play(soundId, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }

}