package com.github.bb441db

import org.junit.Test

class PrintfParserTests {

    private val parser = PrintfParser()

    @Test
    fun testParse() {
        val formatStrings = parser.parse("Test %1\$-3s %2\$d")
        assert(formatStrings.size == 4)

        assert(formatStrings[0] is FixedString) // Test<space>
        assert(formatStrings[1] is FormatSpecifier) // %1$s
        assert(formatStrings[2] is FixedString) // <space>
        assert(formatStrings[3] is FormatSpecifier) // %2$d

        assert((formatStrings[1] as FormatSpecifier).index == 1)
        assert((formatStrings[3] as FormatSpecifier).index == 2)

        assert((formatStrings[1] as FormatSpecifier).conversion == Conversion.String('s'))
        assert((formatStrings[3] as FormatSpecifier).conversion == Conversion.DecimalInt)

        assert((formatStrings[1] as FormatSpecifier).flags.contains(Flag.LeftJustify))
    }

}