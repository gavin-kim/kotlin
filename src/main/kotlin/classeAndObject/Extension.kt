package classeAndObject


/** Kotlin supports extension functions and extension properties */

fun String.lastChar(): Char = this[this.length - 1]
/**    ^                        ^    ^
 *  receiver type          receiver object
 *  (For extension)        (instance of the receiver type)
 */

/** Extension functions are dispatched statically */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1];
    this[index1] = this[index2]
    this[index2] = tmp
}

fun extensionFuntionExamples() {
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2) // 'this' inside swap() will hold the value of list object
}


/** Extension functions can not override existing member */
class Omega {
    fun omega() {
        println("member")
    }
}

fun Omega.omega() { // member always win
    println("extension")
}

fun Omega.omega(some: String) { // can overload
    println("extension with an argument")
}


/** Nullable Receiver */
fun Any?.toString(): String {
    if (this == null) // after the null check 'this' is smart cast to a non-null type
        return "null"

    return toString() // resolves to the member function of the Any class
}


/**
 * Extension Properties
 *
 * Extensions do not actually insert members into classes (No way to have a backing field),
 * So initializer is not allowed for extension properties
 */
val <T> List<T>.lastIndex: Int
    get() = size - 1
