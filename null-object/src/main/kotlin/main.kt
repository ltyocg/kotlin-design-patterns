fun main() = node(
    "1",
    node(
        "11",
        node("111", NullNode, NullNode),
        NullNode
    ),
    node(
        "12",
        NullNode,
        node("122", NullNode, NullNode)
    )
).walk()