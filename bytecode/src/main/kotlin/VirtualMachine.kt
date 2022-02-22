import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class VirtualMachine(wizard1: Wizard, wizard2: Wizard) {
    private val log = LoggerFactory.getLogger(javaClass)

    constructor() : this(
        Wizard(randomInt(3, 32), randomInt(3, 32), randomInt(3, 32), 0, 0),
        Wizard(randomInt(3, 32), randomInt(3, 32), randomInt(3, 32), 0, 0)
    )

    val stack = Stack<Int>()
    val wizards = arrayOf(wizard1, wizard2)

    fun execute(bytecode: IntArray) {
        var i = 0
        while (i < bytecode.size) {
            val instruction = Instruction.getInstruction(bytecode[i])
            when (instruction) {
                Instruction.LITERAL -> stack.push(bytecode[++i])
                Instruction.SET_AGILITY -> with(stack.pop() to stack.pop()) { setAgility(second, first) }
                Instruction.SET_WISDOM -> with(stack.pop() to stack.pop()) { setWisdom(second, first) }
                Instruction.SET_HEALTH -> with(stack.pop() to stack.pop()) { setHealth(second, first) }
                Instruction.GET_AGILITY -> stack.push(getAgility(stack.pop()))
                Instruction.GET_WISDOM -> stack.push(getWisdom(stack.pop()))
                Instruction.GET_HEALTH -> stack.push(getHealth(stack.pop()))
                Instruction.ADD -> stack.push(stack.pop() + stack.pop())
                Instruction.DIVIDE -> stack.push(with(stack.pop() to stack.pop()) { second / first })
                Instruction.PLAY_SOUND -> wizards[stack.pop()].playSound()
                Instruction.SPAWN_PARTICLES -> wizards[stack.pop()].spawnParticles()
            }
            i++
            log.info("Executed {}, Stack contains {}", instruction.name, stack)
        }
    }

    private fun setHealth(wizard: Int, amount: Int) {
        wizards[wizard].health = amount
    }

    private fun setWisdom(wizard: Int, amount: Int) {
        wizards[wizard].wisdom = amount
    }

    private fun setAgility(wizard: Int, amount: Int) {
        wizards[wizard].agility = amount
    }

    private fun getHealth(wizard: Int): Int = wizards[wizard].health
    private fun getWisdom(wizard: Int): Int = wizards[wizard].wisdom
    private fun getAgility(wizard: Int): Int = wizards[wizard].agility

    companion object {
        private fun randomInt(min: Int, max: Int): Int = ThreadLocalRandom.current().nextInt(min, max + 1)
    }
}