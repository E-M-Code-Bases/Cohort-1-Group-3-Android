package com.example.zozamax_app

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import java.io.IOException

object MoviePlayer {
    private var player: ExoPlayer? = null
    private var playerView: PlayerView? = null

    fun initializePlayer(context: Context, playerView: PlayerView, mediaUrl: String) {
        player = try {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(Uri.parse(mediaUrl))
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        } catch (e: IOException) {
            Log.e("MoviePlayer", "Failed to initialize player", e)
            null
        }
        this.playerView = playerView
        playerView?.player = player
    }

    fun releasePlayer() {
        player?.let {
            it.release()
            player = null
            playerView = null
        }
    }
}
