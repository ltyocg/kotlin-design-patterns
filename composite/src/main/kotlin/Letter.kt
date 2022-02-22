class Letter(val character: Char) : LetterComposite() {
    override fun printThisBefore() = print(character)
}