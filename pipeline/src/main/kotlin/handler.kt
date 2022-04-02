import org.slf4j.LoggerFactory

class ConvertToCharArrayHandler : (String) -> CharArray {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke(input: String): CharArray {
        val characters = input.toCharArray()
        log.info(
            "Current handler: {}, input is {} of type {}, output is {}, of type {}",
            this::class,
            input,
            String::class,
            characters.contentToString(),
            CharArray::class
        )
        return characters
    }
}

class RemoveAlphabetsHandler : (String) -> String {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke(input: String): String {
        val inputWithoutAlphabetsStr = input.asSequence()
            .filterNot { Character.isAlphabetic(it.code) }
            .joinToString("")
        log.info(
            "Current handler: {}, input is {} of type {}, output is {}, of type {}",
            this::class,
            input,
            String::class,
            inputWithoutAlphabetsStr,
            String::class
        )
        return inputWithoutAlphabetsStr
    }
}

class RemoveDigitsHandler : (String) -> String {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun invoke(input: String): String {
        val inputWithoutDigitsStr = input.asSequence()
            .filterNot(Char::isDigit)
            .joinToString("")
        log.info(
            "Current handler: {}, input is {} of type {}, output is {}, of type {}",
            this::class,
            input,
            String::class,
            inputWithoutDigitsStr,
            String::class
        )
        return inputWithoutDigitsStr
    }
}