package reference.classesAndObjects

/**
 * Enum class example
 *
 * val name: String = "WAITING", "TALKING"
 * val ordinal: Int = 0, 1
 */

enum class ProtocolState {
    WAITING {
        override fun signal(): ProtocolState {
            return TALKING
        }
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

fun main(args: Array<String>) {
    println("Name:${ProtocolState.WAITING.name}, Ordinal:${ProtocolState.WAITING.ordinal}")
    println("Name:${ProtocolState.TALKING.name}, Ordinal:${ProtocolState.TALKING.ordinal}")
}