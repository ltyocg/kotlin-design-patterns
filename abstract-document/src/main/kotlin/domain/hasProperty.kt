package domain

import Document
import domain.enums.Property

interface HasType : Document {
    val type: String?
        get() = this[Property.TYPE.name] as String?
}

interface HasModel : Document {
    val model: String?
        get() = this[Property.MODEL.name] as String?
}

interface HasPrice : Document {
    val price: Number?
        get() = this[Property.PRICE.name] as Number?
}

interface HasParts : Document {
    val parts: Sequence<Part>
        get() = children(Property.PARTS.name, ::Part)
}