package com.github.bb441db

import java.lang.reflect.Field
import kotlin.reflect.KProperty

internal class ReflectionFieldDelegate<T: Any>(private val target: Any, private val name: String, private val type: Class<T>) {
    private val field by lazy {
        target::class.java.getDeclaredField(name).apply { isAccessible = true }
    }

    operator fun getValue(t: Any, property: KProperty<*>): T {
        return field.get(target, type)
    }

    private fun<T: Any> Field.get(target: Any, type: Class<T>): T {
        return if (this.type.isPrimitive || this.type == String::class.java) {
            return this.get(target) as T
        } else {
            if (!ReflectionProxy::class.java.isAssignableFrom(type)) {
                throw Exception("Unable to cast non-primitive: ${this.type.canonicalName} to ${type.canonicalName}")
            }
            type.getConstructor(Any::class.java).newInstance(this.get(target))
        }
    }
}