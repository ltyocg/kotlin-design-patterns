import com.ltyocg.commons.FieldAccessor
import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertSame

abstract class AbstractHolderTest {
    abstract val internalHeavyValue: Heavy
    abstract val heavy: Heavy

    @Test
    fun `test get heavy`() = assertTimeout(Duration.ofMillis(3000)) {
        assertSame(heavy, internalHeavyValue)
    }
}

class HolderNaiveTest : AbstractHolderTest() {
    private val holder = HolderNaive()
    override val internalHeavyValue: Heavy
        get() = FieldAccessor(FieldAccessor(holder)["heavy\$delegate"])["_value"]
    override val heavy: Heavy
        get() = holder.heavy
}

class HolderThreadSafeTest : AbstractHolderTest() {
    private val holder = HolderThreadSafe()
    override val internalHeavyValue: Heavy
        get() = FieldAccessor(FieldAccessor(holder)["heavy\$delegate"])["_value"]
    override val heavy: Heavy
        get() = holder.heavy
}