class CardUpdateService(private val cardJpaRepository: JpaRepository<Card>) : UpdateService<Card> {
    override fun doUpdate(obj: Card, id: Long): Card {
        val cardToUpdate = cardJpaRepository.findById(id)
        cardToUpdate.sum += obj.sum
        if (cardToUpdate.version != cardJpaRepository.getEntityVersionById(id))
            throw ApplicationException("Entity with id $id were updated in another transaction")
        cardJpaRepository.update(cardToUpdate)
        return cardToUpdate
    }
}
