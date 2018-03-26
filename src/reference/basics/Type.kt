package reference.basics


/** Number bit width **/
val double: Double = 64.0
val float: Float = 32.0F
val long: Long = 64L
val int: Int = 32
val short: Short = 16
val byte: Byte = 8

/** Number with underscores */
val doubleWithUnderScores: Double = 123_456_789.0
val longWithUnderScores: Long = 1_123_456L


/** operations */
fun operations() {
    val signedShiftLeft = 1 shl 2
    val signedShiftRight = 10 shr 2
    val unsignedShiftRight = 10 ushr 2
    var and = (1 == 1) and (2 == 2)
    var or = (1 == 1) or (2 == 2)
    var xor = (1 == 1) xor (2 == 2)
    var bitInversion = 8.inv()
}