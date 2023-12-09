package choreography

class ServiceDiscoveryService {
    private val services = mutableMapOf<String, ChoreographyChapter>()
    fun findAny(): ChoreographyChapter = services.values.first()
    fun find(service: String): ChoreographyChapter? = services.getOrDefault(service, null)
    fun discover(chapterService: ChoreographyChapter): ServiceDiscoveryService = apply {
        services[chapterService.name] = chapterService
    }
}
