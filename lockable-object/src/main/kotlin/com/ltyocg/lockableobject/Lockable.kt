package com.ltyocg.lockableobject

import com.ltyocg.lockableobject.domain.Creature

interface Lockable {
    val isLocked: Boolean
    fun lock(creature: Creature): Boolean
    fun unlock(creature: Creature)
    val locker: Creature?
    val name: String
}