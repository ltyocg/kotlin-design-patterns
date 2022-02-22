import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class VirtualMachineTest {
    @Test
    fun `test literal`() {
        val vm = VirtualMachine()
        vm.execute(intArrayOf(Instruction.LITERAL.intValue, 10))
        assertEquals(1, vm.stack.size)
        assertEquals(10, vm.stack.pop())
    }

    @Test
    fun `test SetHealth`() {
        val wizardNumber = 0
        val vm = VirtualMachine()
        vm.execute(
            intArrayOf(
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.LITERAL.intValue,
                50,
                Instruction.SET_HEALTH.intValue
            )
        )
        assertEquals(50, vm.wizards[wizardNumber].health)
    }

    @Test
    fun `test SetAgility`() {
        val wizardNumber = 0
        val vm = VirtualMachine()
        vm.execute(
            intArrayOf(
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.LITERAL.intValue,
                50,
                Instruction.SET_AGILITY.intValue
            )
        )
        assertEquals(50, vm.wizards[wizardNumber].agility)
    }

    @Test
    fun `test GetHealth`() {
        val wizardNumber = 0
        val vm = VirtualMachine()
        vm.execute(
            intArrayOf(
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.LITERAL.intValue,
                50,
                Instruction.SET_HEALTH.intValue,
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.GET_HEALTH.intValue
            )
        )
        assertEquals(50, vm.stack.pop())
    }

    @Test
    fun `test PlaySound`() {
        val wizardNumber = 0
        val vm = VirtualMachine()
        vm.execute(
            intArrayOf(
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.PLAY_SOUND.intValue,
            )
        )
        assertEquals(0, vm.stack.size)
        assertEquals(1, vm.wizards[0].numberOfPlayedSounds)
    }

    @Test
    fun `test SpawnParticles`() {
        val wizardNumber = 0
        val vm = VirtualMachine()
        vm.execute(
            intArrayOf(
                Instruction.LITERAL.intValue,
                wizardNumber,
                Instruction.SPAWN_PARTICLES.intValue,
            )
        )
        assertEquals(0, vm.stack.size)
        assertEquals(1, vm.wizards[0].numberOfSpawnedParticles)
    }

    @Test
    fun `test invalid instruction`() {
        assertFailsWith<IllegalArgumentException> { VirtualMachine().execute(intArrayOf(999)) }
    }
}