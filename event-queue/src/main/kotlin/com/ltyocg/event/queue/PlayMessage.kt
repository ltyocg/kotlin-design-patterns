package com.ltyocg.event.queue

import javax.sound.sampled.AudioInputStream

class PlayMessage(val stream: AudioInputStream, var volume: Float)