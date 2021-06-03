package com.example.microfone

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    var grabar: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grabar = findViewById<View>(R.id.txtGrabarVoz) as TextView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY -> if (resultCode == RESULT_OK && null != data) {
                val speech = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val strSpeech2Text = speech!![0]
                grabar!!.text = strSpeech2Text
            }
            else -> {
            }
        }
    }

    fun onClickImgBtnHablar(v: View?) {
        val intentActionRecognizeSpeech = Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX")
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext,
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val RECOGNIZE_SPEECH_ACTIVITY = 1
    }
}