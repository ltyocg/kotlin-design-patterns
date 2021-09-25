package com.ltyocg.event.asynchronous

class EventDoesNotExistException(message: String?) : Exception(message)
class InvalidOperationException(message: String?) : Exception(message)
class LongRunningEventException(message: String?) : Exception(message)
class MaxNumOfEventsAllowedException(message: String?) : Exception(message)
