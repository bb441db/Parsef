package com.github.bb441db

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import kotlin.reflect.KProperty

internal class ReflectionFieldDelegate<T: Any>(private val target: Any, private val name: String, private val type: Class<T>) {
    private val field by lazy {
        target::class.java.getDeclaredField(name).apply { isAccessible = true }
    }

    private val finalValue by lazy {
        field.get(target, type)
    }

    operator fun getValue(t: Any, property: KProperty<*>): T {
        return if (field.isFinal) { // final fields only
            finalValue
        } else field.get(target, type)
    }

    private val Field.isFinal: Boolean get() = Modifier.isFinal(this.modifiers)

    private fun<T: Any> Field.get(target: Any, type: Class<T>): T {
        return if (ReflectionProxy::class.java.isAssignableFrom(type)) {
            type.getConstructor(Any::class.java).newInstance(this.get(target))
        } else {
            this.get(target) as T
        }
    }
}