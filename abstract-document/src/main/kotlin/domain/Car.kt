package domain

import AbstractDocument

class Car(properties: Map<String, Any?>) : AbstractDocument(properties), HasModel, HasPrice, HasParts