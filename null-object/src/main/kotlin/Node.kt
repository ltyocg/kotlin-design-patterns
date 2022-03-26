/**
 * Interface for binary tree node.
 */
interface Node {
    val name: String?
    val treeSize: Int
    val left: Node?
    val right: Node?
    fun walk()
}