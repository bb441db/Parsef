package com.github.bb441db

sealed class Conversion(val char: kotlin.Char) {

    abstract val options: Array<kotlin.Char>

    val isUpperCase: kotlin.Boolean get() = char.isUpperCase()

    override fun equals(other: Any?): kotlin.Boolean {
        return other != null
                && other is Conversion
                && other::class.java == this::class.java
                && other.char == this.char
    }

    override fun toString(): kotlin.String {
        return "Conversion(value='$char', isUpperCase=$isUpperCase, options=${options.joinToString(", ") { "'$it'" }})"
    }

    override fun hashCode(): Int {
        return 31 * char.hashCode() + options.contentHashCode()
    }

    companion object {
        fun from(char: kotlin.Char): Conversion {
            return when (char) {
                'b', 'B' -> Boolean(char)
                'h', 'H' -> HexString(char)
                's', 'S' -> String(char)
                'c', 'C' -> Char(char)
                'd' -> DecimalInt
                'o' -> Octal
                'x', 'X' -> HexInt(char)
                'e', 'E' -> DecimalScientific(char)
                'f' -> DecimalNumber
                'g', 'G' -> DecimalScientificIfPrecision(char)
                't', 'T' -> DateTime(char)
                '%' -> Percent
                'n' -> Line
                else -> throw Exception("Unknown conversion: '$char'")
            }
        }
    }

    class Boolean(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('b', 'B')
    }

    class HexString(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('h', 'H')
    }

    class String(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('s', 'S')
    }

    class Char(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('c', 'C')
    }

    object DecimalInt : Conversion('d') {
        override val options: Array<kotlin.Char> = arrayOf('d')
    }

    object Octal : Conversion('o') {
        override val options: Array<kotlin.Char> = arrayOf('o')
    }

    class HexInt(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('x', 'X')
    }

    class DecimalScientific(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('e', 'E')
    }

    object DecimalNumber : Conversion('f') {
        override val options: Array<kotlin.Char> = arrayOf('f')
    }

    class DecimalScientificIfPrecision(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('g', 'G')
    }

    class DateTime(char: kotlin.Char) : Conversion(char) {
        override val options: Array<kotlin.Char> = arrayOf('t', 'T')
    }

    object Percent : Conversion('%') {
        override val options: Array<kotlin.Char> = arrayOf('%')
    }

    object Line : Conversion('n') {
        override val options: Array<kotlin.Char> = arrayOf('n')
    }
}