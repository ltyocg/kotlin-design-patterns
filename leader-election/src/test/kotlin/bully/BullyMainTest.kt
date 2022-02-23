package bully

import BullyMain
import kotlin.test.Test

class BullyMainTest {
    @Test
    fun `should execute main without exception`() {
        BullyMain.main(emptyArray())
    }
}