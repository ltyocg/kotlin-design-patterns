interface Prototype {
    operator fun get(stat: Stats): Int?
    operator fun contains(stat: Stats): Boolean
    operator fun set(stat: Stats, `val`: Int)
    fun remove(stat: Stats)
}