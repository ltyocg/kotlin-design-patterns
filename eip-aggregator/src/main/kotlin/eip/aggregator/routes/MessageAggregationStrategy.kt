package eip.aggregator.routes

import org.apache.camel.AggregationStrategy
import org.apache.camel.Exchange
import org.springframework.stereotype.Component

@Component
class MessageAggregationStrategy : AggregationStrategy {
    override fun aggregate(oldExchange: Exchange?, newExchange: Exchange): Exchange {
        if (oldExchange == null) return newExchange
        oldExchange.getIn().body = "${oldExchange.getIn().body};${newExchange.getIn().body}"
        return oldExchange
    }
}