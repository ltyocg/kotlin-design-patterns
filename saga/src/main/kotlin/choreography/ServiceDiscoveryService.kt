package choreography

class ServiceDiscoveryService {
    private val services = mutableMapOf<String, ChoreographyChapter>()
    fun findAny(): ChoreographyChapter = services.values.first()
    fun find(service: String): ChoreographyChapter? = services.getOrDefault(service, null)
    fun discover(chapterService: ChoreographyChapter): ServiceDiscoveryService {
        services[chapterService.name] = chapterService
        return this
    }
}