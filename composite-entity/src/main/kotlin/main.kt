import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    with(CompositeEntity()) {
        init()
        setData("No Danger", "Green Light")
        getData().forEach(log::info)
        setData("Danger", "Red Light")
        getData().forEach(log::info)
    }
}