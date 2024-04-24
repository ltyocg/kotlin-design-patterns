package health.check

class HealthCheckInterruptedException(cause: Throwable?) : RuntimeException("Health check interrupted", cause)
