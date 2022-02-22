import java.time.DayOfWeek

fun main() {
    val kingsHand = KingsHand(KingJoffrey())
    val emitters = listOf(kingsHand, LordBaelish(kingsHand), LordVarys(kingsHand), Scout(kingsHand))
    DayOfWeek.values()
        .map { { emitter: EventEmitter -> emitter.timePasses(it) } }
        .forEach(emitters::forEach)
}