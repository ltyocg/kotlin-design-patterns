open class BusinessException(message: String?) : Exception(message)
class CustomerNotFoundException(message: String?) : BusinessException(message)
class DatabaseNotAvailableException(message: String?) : BusinessException(message)