package com.ltyocg.abstractdocument.domain

import com.ltyocg.abstractdocument.AbstractDocument

class Car(properties: Map<String, Any?>) : AbstractDocument(properties), HasModel, HasPrice, HasParts