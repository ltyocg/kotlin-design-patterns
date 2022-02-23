package bst

open class TreeNode<T : Comparable<T>>(val value: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
    fun insert(valToInsert: T) {
        var parent = this
        var curr: TreeNode<T>? = this
        while (curr != null) {
            parent = curr
            curr = if (curr.value > valToInsert) curr.left else curr.right
        }
        if (parent.value <= valToInsert) parent.right = TreeNode(valToInsert)
        else parent.left = TreeNode(valToInsert)
    }
}