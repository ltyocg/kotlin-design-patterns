package com.ltyocg.abstractdocument.domain

import com.ltyocg.abstractdocument.Document
import com.ltyocg.abstractdocument.domain.enums.Property

interface HasType : Document {
    val type: String?
        get() = get(Property.TYPE.name) as String?
}

interface HasModel : Document {
    val model: String?
        get() = get(Property.MODEL.name) as String?
}

interface HasPrice : Document {
    val price: Number?
        get() = get(Property.PRICE.name) as Number?
}

interface HasParts : Document {
    val parts: Sequence<Part>
        get() = children(Property.PARTS.name, ::Part)
}