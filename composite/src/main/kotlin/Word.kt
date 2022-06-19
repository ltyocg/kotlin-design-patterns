class Word(vararg letters: Char) : LetterComposite() {
    init {
        letters.forEach { add(Letter(it)) }
    }

    override fun printThisBefore() = print(" ")
}