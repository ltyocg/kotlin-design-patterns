class Servant(var name: String) {
    fun feed(r: Royalty) = r.getFed()
    fun giveWine(r: Royalty) = r.getDrink()
    fun giveCompliments(r: Royalty) = r.receiveCompliments()
    fun checkIfYouWillBeHanged(tableGuests: List<Royalty>): Boolean = tableGuests.all { it.mood }
}