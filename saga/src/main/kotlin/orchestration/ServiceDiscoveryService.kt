package orchestration

class ServiceDiscoveryService {
    private val services = mutableMapOf<String, OrchestrationChapter<*>>()
    fun find(service: String): OrchestrationChapter<*>? = services[service]
    fun discover(orchestrationChapterService: OrchestrationChapter<*>): ServiceDiscoveryService = apply {
        services[orchestrationChapterService.name] = orchestrationChapterService
    }
}
