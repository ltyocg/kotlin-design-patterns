package com.ltyocg.abstractdocument.domain

import com.ltyocg.abstractdocument.AbstractDocument

class Part(properties: Map<String, Any?>) : AbstractDocument(properties), HasType, HasModel, HasPrice