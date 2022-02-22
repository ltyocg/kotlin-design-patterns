package domain

import AbstractDocument

class Part(properties: Map<String, Any?>) : AbstractDocument(properties), HasType, HasModel, HasPrice