package com.github.bb441db

import java.util.*

class FormatterExtensions {
    companion object {
        private val fixedStringClass = Class.forName("java.util.Formatter\$FixedString")
        private val formatSpecifierClass = Class.forName("java.util.Formatter\$FormatSpecifier")
        private val parseDelegate by lazy {
            Formatter::class.java.getDeclaredMethod("parse", String::class.java).apply {
                isAccessible = true
            }
        }

        @JvmStatic
        @JvmOverloads
        fun parsef(value: String, formatter: Formatter = Formatter()): List<FormatString> {
            val rawValues = parseDelegate.invoke(formatter, value) as? Collection<*> ?: throw Exception("Expecting collection")
            return rawValues
                .filterNotNull()
                .map {
                    when (it.javaClass) {
                        fixedStringClass -> FixedString(it)
                        formatSpecifierClass -> FormatSpecifier(it)
                        else -> throw Exception("Unknown ${it::class.java.canonicalName}")
                    }
                }
        }
    }
}
