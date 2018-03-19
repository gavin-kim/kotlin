package strings



fun String.lastChar(): Char = this[this.length - 1]

/**
 * Utility function as extensions
 */
@JvmOverloads
fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "[",
        postfix: String = "]"
): String {
    val result = StringBuilder(prefix)

    for ((index, value) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(value)
    }

    return result.append(postfix).toString()
}

