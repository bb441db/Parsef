package com.github.bb441db

import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import java.lang.Appendable
import java.nio.charset.Charset
import java.util.*

class PrintfParser(private val formatter: Formatter = Formatter()) {
    constructor(appendable: Appendable) : this(Formatter(appendable))
    constructor(locale: Locale) : this(Formatter(locale))
    constructor(appendable: Appendable, locale: Locale) : this(Formatter(appendable, locale))
    constructor(fileName: String) : this(Formatter(fileName))
    constructor(fileName: String, csn: String) : this(Formatter(fileName, csn))
    constructor(fileName: String, csn: String, locale: Locale) : this(Formatter(fileName, csn, locale))
    constructor(fileName: String, charset: Charset, locale: Locale) : this(Formatter(fileName, charset, locale))
    constructor(file: File) : this(Formatter(file))
    constructor(file: File, csn: String) : this(Formatter(file, csn))
    constructor(file: File, csn: String, locale: Locale) : this(Formatter(file, csn, locale))
    constructor(ps: PrintStream) : this(Formatter(ps))
    constructor(os: OutputStream) : this(Formatter(os))
    constructor(os: OutputStream, csn: String) : this(Formatter(os, csn))
    constructor(os: OutputStream, csn: String, locale: Locale) : this(Formatter(os, csn, locale))

    fun parse(value: String): List<FormatString> {
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

    companion object {
        private val fixedStringClass = Class.forName("java.util.Formatter\$FixedString")
        private val formatSpecifierClass = Class.forName("java.util.Formatter\$FormatSpecifier")
        private val parseDelegate by lazy {
            Formatter::class.java.getDeclaredMethod("parse", String::class.java).apply {
                isAccessible = true
            }
        }
    }
}
