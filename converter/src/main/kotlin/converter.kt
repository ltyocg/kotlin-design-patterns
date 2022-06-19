open class Converter<T, U>(
    private val fromDto: (T) -> U,
    private val fromEntity: (U) -> T
) {
    fun convertFromDto(dto: T): U = fromDto(dto)
    fun convertFromEntity(entity: U): T = fromEntity(entity)
    fun createFromDtos(dtos: Collection<T>): List<U> = dtos.map(fromDto)
    fun createFromEntities(entities: Collection<U>): List<T> = entities.map(fromEntity)
}

object UserConverter : Converter<UserDto, User>(
    { User(it.firstName, it.lastName, it.active, it.email) },
    { UserDto(it.firstName, it.lastName, it.active, it.userId) }
)