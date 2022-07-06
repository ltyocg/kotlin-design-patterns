object NullNode : Node {
    override val treeSize = 0
    override val left: Node? = null
    override val right: Node? = null
    override val name: String? = null
    override fun walk() {}
}