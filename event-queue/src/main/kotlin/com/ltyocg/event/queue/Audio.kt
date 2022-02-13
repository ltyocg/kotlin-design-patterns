package com.ltyocg.event.queue

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineUnavailableException
import kotlin.math.max

class Audio {
    private val log = LoggerFactory.getLogger(javaClass)
    private var headIndex = 0
    private var tailIndex = 0
    private var updateJob: Job? = null

    @Suppress("UNCHECKED_CAST")
    val pendingAudio = arrayOfNulls<PlayMessage>(MAX_PENDING) as Array<PlayMessage>

    companion object {
        private const val MAX_PENDING = 16
        val INSTANCE = Audio()
    }

    @Synchronized
    fun stopService() = runBlocking {
        updateJob?.cancelAndJoin()
        updateJob = null
    }

    val isServiceRunning: Boolean
        @Synchronized
        get() = updateJob?.isActive == true

    fun playSound(stream: AudioInputStream, volume: Float) {
        if (updateJob == null) updateJob = CoroutineScope(Dispatchers.IO).launch(start = CoroutineStart.LAZY) {
            while (isActive) if (headIndex != tailIndex) try {
                with(AudioSystem.getClip()) {
                    @Suppress("BlockingMethodInNonBlockingContext")
                    open(pendingAudio[headIndex++].stream)
                    start()
                }
            } catch (e: LineUnavailableException) {
                log.trace("Error occoured while loading the audio: The line is unavailable", e)
            }
        }
        startThread()
        var i = headIndex
        while (i != tailIndex) {
            pendingAudio[i].let {
                if (it.stream === stream) {
                    it.volume = max(volume, it.volume)
                    return
                }
            }
            i = (i + 1) % MAX_PENDING
        }
        pendingAudio[tailIndex] = PlayMessage(stream, volume)
        tailIndex = (tailIndex + 1) % MAX_PENDING
    }

    @Synchronized
    private fun startThread() {
        if (!updateJob!!.isActive) {
            updateJob!!.start()
            headIndex = 0
            tailIndex = 0
        }
    }

    fun getAudioStream(filePath: String): AudioInputStream = AudioSystem.getAudioInputStream(File(filePath).absoluteFile)
}