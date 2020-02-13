package com.github.bb441db

sealed class FormatString : ReflectionProxy()

class FixedString(raw: Any) : FormatString() {
    val string: String by reflect(raw, "s")
    val start: Int by reflect(raw, "start")
    val end: Int by reflect(raw, "start")

    override fun toString(): String {
        return "FixedString(string=\"$string\", start=$start, end=$end)"
    }
}

class FormatSpecifier(raw: Any) : FormatString() {
    val index: Int by reflect(raw, "index")
    val width: Int by reflect(raw, "width")
    val precision: Int by reflect(raw, "precision")
    val isDateTime: Boolean by reflect(raw, "dt")
    val conversionRaw: Char by reflect(raw, "c")
    val conversion by lazy { Conversion.from(conversionRaw) }
    val flags: Flags by reflect(raw, "f")

    override fun toString(): String {
        return "FormatSpecifier(index=$index, width=$width, precision=$precision, isDateTime=$isDateTime, conversion=$conversion, flags=$flags)"
    }
}