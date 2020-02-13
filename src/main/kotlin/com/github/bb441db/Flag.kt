package com.github.bb441db

enum class Flag(val raw: Int, val char: Char) {
    None(0, '\u0000'),

    LeftJustify(1 shl 0, '-'),
    Uppercase(1 shl 1, '^'),
    Alternate(1 shl 2, '#'),

    Plus(1 shl 3, '+'),
    LeadingSpace(1 shl 4, ' '),
    ZeroPad(1 shl 5, '0'),
    Group(1 shl 6, ','),
    Parentheses(1 shl 7, '('),

    Previous(1 shl 8, '<');

    override fun toString(): String {
        return "Flag(raw=$raw, char='$char')"
    }

    companion object {
        fun from(raw: Int): Array<Flag> {
            return values().mapNotNull {
                if ((it.raw and raw) == it.raw) it else null
            }.toTypedArray()
        }
    }
}