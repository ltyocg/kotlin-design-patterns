package com.ltyocg.compositeentity

abstract class DependentObject<T> {
    var data: T? = null
}

class MessageDependentObject : DependentObject<String>()
class SignalDependentObject : DependentObject<String>()