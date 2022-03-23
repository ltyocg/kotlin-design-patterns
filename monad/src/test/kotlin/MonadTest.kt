import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class MonadTest {
    @Test
    fun `invalid name`() {
        assertFailsWith<IllegalStateException> {
            Validator.of(User(null, 21, Sex.MALE, "tom@foo.bar"))
                .validate(User::name, { it != null }, "name cannot be null")
                .get()
        }
    }

    @Test
    fun `invalid age`() {
        assertFailsWith<IllegalStateException> {
            Validator.of(User("John", 17, Sex.MALE, "john@qwe.bar"))
                .validate(User::name, { it != null }, "name cannot be null")
                .validate(User::age, { it > 21 }, "user is underage")
                .get()
        }
    }

    @Test
    fun valid() {
        val sarah = User("Sarah", 42, Sex.FEMALE, "sarah@det.org")
        assertSame(
            Validator.of(sarah)
                .validate(User::name, { it != null }, "name cannot be null")
                .validate(User::age, { it > 21 }, "user is underage")
                .validate(User::sex, { it == Sex.FEMALE }, "user is not female")
                .validate(User::email, { "@" in it }, "email does not contain @ sign")
                .get(),
            sarah
        )
    }
}