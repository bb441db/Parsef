# Parsef

Exposes `Formatter.parse(s: String): List<FormatString>` using reflection

### Usage 

**Kotlin**
```kt
parsef("Example %1\$s")
```

**Java**
```kt
FormatterExtensions.parsef("Example %1$s")
```


### TODO
- Verify implementation of `java.util.Formatter` are equal on different jvm targets
