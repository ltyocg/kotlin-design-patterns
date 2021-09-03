package com.ltyocg.composite

abstract class LetterComposite {
    private val children = mutableListOf<LetterComposite>()
    fun add(letter: LetterComposite) {
        children.add(letter)
    }

    fun count(): Int = children.size
    protected open fun printThisBefore() {}
    protected open fun printThisAfter() {}
    fun print() {
        printThisBefore()
        children.forEach(LetterComposite::print)
        printThisAfter()
    }
}