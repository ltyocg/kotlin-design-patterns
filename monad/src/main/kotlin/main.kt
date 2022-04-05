import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    Validator(User("user", 24, Sex.FEMALE, "foobar.com"))
        .validate(User::name, { it?.isNotEmpty() ?: false }, "name is empty")
        .validate(User::email, { "@" !in it }, "email doesn't contains '@'")
        .validate(User::age, { it in 21..29 }, "age isn't between...")
        .get()
        .also { log.info(it.toString()) }
}