package com.ltyocg.abstractdocument.domain

import com.ltyocg.abstractdocument.Document
import com.ltyocg.abstractdocument.domain.enums.Property

interface HasType : Document {
    fun getType(): String? = get(Property.TYPE.name) as String
}

interface HasModel : Document {
    fun getModel(): String? = get(Property.MODEL.name) as String
}

interface HasPrice : Document {
    fun getPrice(): Number? = get(Property.PRICE.name) as Number
}

interface HasParts : Document {
    fun getParts(): Sequence<Part> = children(Property.PARTS.name, ::Part)
}