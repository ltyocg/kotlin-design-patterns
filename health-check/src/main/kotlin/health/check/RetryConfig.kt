package health.check

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.backoff.FixedBackOffPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate

@Configuration
class RetryConfig(
    @Value("\${retry.backoff.period:2000}")
    private val backOffPeriod: Long,
    @Value("\${retry.max.attempts:3}")
    private val maxAttempts: Int
) {
    @Bean
    fun retryTemplate(): RetryTemplate = RetryTemplate().apply {
        setBackOffPolicy(FixedBackOffPolicy().apply {
            backOffPeriod = this@RetryConfig.backOffPeriod
        })
        setRetryPolicy(SimpleRetryPolicy().apply {
            maxAttempts = this@RetryConfig.maxAttempts
        })
    }
}
