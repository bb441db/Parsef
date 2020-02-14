package com.github.bb441db

import java.util.*

fun parsef(value: String, formatter: Formatter = Formatter()): List<FormatString> = FormatterExtensions.parsef(value, formatter)