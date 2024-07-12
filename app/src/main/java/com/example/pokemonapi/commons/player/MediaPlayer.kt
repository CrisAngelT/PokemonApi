package com.example.pokemonapi.commons.player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UtilMediaPlayer
{
    private var mediaPlayer: MediaPlayer? = null

    fun playAudioFromUrl(audioUrl:String,context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer()
                } else {
                    mediaPlayer?.reset()  // Reset mediaPlayer if it already exists
                }

                mediaPlayer?.setDataSource(context, Uri.parse(audioUrl))
                mediaPlayer?.setOnPreparedListener {
                    it.start()
                }
                mediaPlayer?.prepareAsync()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun play() {
        mediaPlayer?.start()
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
