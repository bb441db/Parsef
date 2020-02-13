package com.github.bb441db

class Flags(raw: Any) : ReflectionProxy() {
    val rawFlags: Int by reflect(raw, "flags")
    val flags by lazy { Flag.from(rawFlags) }

    fun contains(flag: Flag): Boolean = this.flags.contains(flag)

    override fun toString(): String {
        return "Flags(rawFlags=$rawFlags, flags=[${flags.joinToString(", ")}])"
    }
}