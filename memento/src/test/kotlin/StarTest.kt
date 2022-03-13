import kotlin.test.Test
import kotlin.test.assertEquals

class StarTest {
    @Test
    fun timePasses() {
        val star = Star(StarType.SUN, 1, 2)
        assertEquals("sun age: 1 years mass: 2 tons", star.toString())
        star.timePasses()
        assertEquals("red giant age: 2 years mass: 16 tons", star.toString())
        star.timePasses()
        assertEquals("white dwarf age: 4 years mass: 128 tons", star.toString())
        star.timePasses()
        assertEquals("supernova age: 8 years mass: 1024 tons", star.toString())
        star.timePasses()
        assertEquals("dead star age: 16 years mass: 8192 tons", star.toString())
        star.timePasses()
        assertEquals("dead star age: 64 years mass: 0 tons", star.toString())
        star.timePasses()
        assertEquals("dead star age: 256 years mass: 0 tons", star.toString())
    }

    @Test
    fun `set memento`() {
        val star = Star(StarType.SUN, 1, 2)
        val firstMemento = star.memento
        assertEquals("sun age: 1 years mass: 2 tons", star.toString())
        star.timePasses()
        val secondMemento = star.memento
        assertEquals("red giant age: 2 years mass: 16 tons", star.toString())
        star.timePasses()
        val thirdMemento = star.memento
        assertEquals("white dwarf age: 4 years mass: 128 tons", star.toString())
        star.timePasses()
        assertEquals("supernova age: 8 years mass: 1024 tons", star.toString())
        star.memento = thirdMemento
        assertEquals("white dwarf age: 4 years mass: 128 tons", star.toString())
        star.timePasses()
        assertEquals("supernova age: 8 years mass: 1024 tons", star.toString())
        star.memento = secondMemento
        assertEquals("red giant age: 2 years mass: 16 tons", star.toString())
        star.memento = firstMemento
        assertEquals("sun age: 1 years mass: 2 tons", star.toString())
    }
}