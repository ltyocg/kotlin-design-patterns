import io.github.oshai.kotlinlogging.KotlinLogging

fun node(name: String, left: Node, right: Node) = NodeImpl(name, left, right)
sealed interface Node {
    val name: String?
    val treeSize: Int
    val left: Node?
    val right: Node?
    fun walk()
}

class NodeImpl(
    override val name: String,
    override val left: Node,
    override val right: Node
) : Node {
    private val logger = KotlinLogging.logger {}
    override val treeSize = 1 + left.treeSize + right.treeSize
    override fun walk() {
        logger.info { name }
        if (left.treeSize > 0) left.walk()
        if (right.treeSize > 0) right.walk()
    }
}
