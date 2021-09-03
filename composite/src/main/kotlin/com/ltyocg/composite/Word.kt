package com.ltyocg.composite

class Word : LetterComposite {
    constructor(letters: List<Letter>) {
        letters.forEach(this::add)
    }

    constructor(vararg letters: Char) {
        letters.forEach { add(Letter(it)) }
    }

    override fun printThisBefore() {
        print(" ")
    }
}