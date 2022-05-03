package orchestration

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class SagaOrchestratorInternallyTest {
    private val records = mutableListOf<String>()

    @Test
    fun execute() {
        assertEquals(
            Saga.Result.ROLLBACK,
            SagaOrchestrator(
                Saga()
                    .chapter("1")
                    .chapter("2")
                    .chapter("3")
                    .chapter("4"),
                ServiceDiscoveryService()
                    .discover(object : Service<Int>() {
                        override val name = "1"
                        override fun process(value: Int): ChapterResult<Int> {
                            records.add("+1")
                            return ChapterResult.success(value)
                        }

                        override fun rollback(value: Int): ChapterResult<Int> {
                            records.add("-1")
                            return ChapterResult.success(value)
                        }
                    })
                    .discover(object : Service<Int>() {
                        override val name = "2"
                        override fun process(value: Int): ChapterResult<Int> {
                            records.add("+2")
                            return ChapterResult.success(value)
                        }

                        override fun rollback(value: Int): ChapterResult<Int> {
                            records.add("-2")
                            return ChapterResult.success(value)
                        }
                    })
                    .discover(object : Service<Int>() {
                        override val name = "3"
                        override fun process(value: Int): ChapterResult<Int> {
                            records.add("+3")
                            return ChapterResult.success(value)
                        }

                        override fun rollback(value: Int): ChapterResult<Int> {
                            records.add("-3")
                            return ChapterResult.success(value)
                        }
                    })
                    .discover(object : Service<Int>() {
                        override val name = "4"
                        override fun process(value: Int): ChapterResult<Int> {
                            records.add("+4")
                            return ChapterResult.failure(value)
                        }

                        override fun rollback(value: Int): ChapterResult<Int> {
                            records.add("-4")
                            return ChapterResult.success(value)
                        }
                    })
            ).execute(1)
        )
        assertContentEquals(arrayOf("+1", "+2", "+3", "+4", "-4", "-3", "-2", "-1"), records.toTypedArray())
    }
}