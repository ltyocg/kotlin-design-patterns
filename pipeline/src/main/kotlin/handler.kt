import io.github.oshai.kotlinlogging.KotlinLogging

class ConvertToCharArrayHandler : (String) -> CharArray {
    private val logger = KotlinLogging.logger {}
    override fun invoke(input: String): CharArray {
        val characters = input.toCharArray()
        logger.info { "Current handler: ${this::class}, input is $input of type ${String::class}, output is ${characters.contentToString()}, of type ${CharArray::class}" }
        return characters
    }
}

class RemoveAlphabetsHandler : (String) -> String {
    private val logger = KotlinLogging.logger {}
    override fun invoke(input: String): String {
        val inputWithoutAlphabetsStr = input.asSequence()
            .filterNot { Character.isAlphabetic(it.code) }
            .joinToString("")
        logger.info { "Current handler: ${this::class}, input is $input of type ${String::class}, output is $inputWithoutAlphabetsStr, of type ${String::class}" }
        return inputWithoutAlphabetsStr
    }
}

class RemoveDigitsHandler : (String) -> String {
    private val logger = KotlinLogging.logger {}
    override fun invoke(input: String): String {
        val inputWithoutDigitsStr = input.asSequence()
            .filterNot(Char::isDigit)
            .joinToString("")
        logger.info { "Current handler: ${this::class}, input is $input of type ${String::class}, output is $inputWithoutDigitsStr, of type ${String::class}" }
        return inputWithoutDigitsStr
    }
}
