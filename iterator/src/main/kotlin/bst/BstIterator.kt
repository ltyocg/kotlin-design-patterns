package bst

class BstIterator<T : Comparable<T>>(root: TreeNode<T>) : Iterator<TreeNode<T>> {
    private val pathStack = ArrayDeque<TreeNode<T>>()

    init {
        pushPathToNextSmallest(root)
    }

    override fun hasNext(): Boolean = pathStack.isNotEmpty()
    override fun next(): TreeNode<T> {
        if (pathStack.isEmpty()) throw NoSuchElementException()
        return pathStack.removeFirst().also { pushPathToNextSmallest(it.right) }
    }

    private fun pushPathToNextSmallest(node: TreeNode<T>?) {
        var tempNode = node
        while (tempNode != null) {
            pathStack.addFirst(tempNode)
            tempNode = tempNode.left
        }
    }
}