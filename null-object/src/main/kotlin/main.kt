fun main() = NodeImpl(
    "1",
    NodeImpl(
        "11",
        NodeImpl("111", NullNode.instance, NullNode.instance),
        NullNode.instance
    ),
    NodeImpl(
        "12",
        NullNode.instance,
        NodeImpl("122", NullNode.instance, NullNode.instance)
    )
).walk()