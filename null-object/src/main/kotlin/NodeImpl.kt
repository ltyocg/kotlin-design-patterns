import org.slf4j.LoggerFactory

class NodeImpl(
    override val name: String,
    override val left: Node,
    override val right: Node
) : Node {
    private val log = LoggerFactory.getLogger(javaClass)
    override val treeSize = 1 + left.treeSize + right.treeSize
    override fun walk() {
        log.info(name)
        if (left.treeSize > 0) left.walk()
        if (right.treeSize > 0) right.walk()
    }
}