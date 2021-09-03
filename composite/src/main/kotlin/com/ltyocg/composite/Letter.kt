package com.ltyocg.composite

class Letter(val character: Char) : LetterComposite() {
    override fun printThisBefore() {
        print(character)
    }
}