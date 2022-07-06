class Sentence(words: List<Word>) : LetterComposite() {
    init {
        words.forEach(::add)
    }

    override fun printThisAfter() = println(".")
}