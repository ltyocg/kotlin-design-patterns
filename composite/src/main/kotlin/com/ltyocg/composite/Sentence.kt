package com.ltyocg.composite

class Sentence(words: List<Word>) : LetterComposite() {
    init {
        words.forEach(this::add)
    }

    override fun printThisAfter() {
        println(".")
    }
}