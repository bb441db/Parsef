package com.github.bb441db

abstract class ReflectionProxy internal constructor() {
    internal fun<T: Any> reflect(target: Any, name: String, type: Class<T>): ReflectionFieldDelegate<T> {
        return ReflectionFieldDelegate(target, name, type)
    }

    internal inline fun<reified T: Any> reflect(target: Any, name: String) = reflect(target, name, T::class.java)
}