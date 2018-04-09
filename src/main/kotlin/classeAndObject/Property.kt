package classeAndObject

import java.util.*

val list = listOf(1, 2, 3)

/** You can omit the type if it can be inferred from the getter */
val isEmpty
    get() = list.isEmpty()


var stringRepresentation: String
    get() {
        return "string"
    }
    set(value) {
        // Do something
    }


/** Change the visibility of setter (visibility of getter must be the same as property) */
var setterVisibility: String = "abc"
    private set(value) {

    }

/** Annotate the sett with Inject */
var setterWithAnnotation: Any? = null
    @Inject set

/** Backing field can be referenced in getter using 'field' directly */
var counter = 0 // initializer assigns the backing field directly
    set(value) {
        if (value >= 0) {
            /** field can be used in the accessors of the property */
            field = value
        }
    }


/** Custom Backing Properties */
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap()
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }


/**
 * Compile-Time Constants
 *
 * 1. Top-level or member of an object
 * 2. Initialized with primitive value or String type value
 * 3. NO custom getter
 */
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

/** const properties can be used in annotation*/
@Deprecated(SUBSYSTEM_DEPRECATED)
fun foo() {

}

/** Late-initialized properties and variables */
class Test {
    lateinit var scanner: Scanner /** Type must be non-null(Scanner?) and not primitive */
    lateinit var string: String

    fun setup() {
        scanner = Scanner(System.`in`)
    }

    fun test() {
        scanner.next()
    }
}

/** Elvis Operator */
fun elvisOverator(list: List<String>?) {
    val value: Int = if (list != null) list.size else -1
    val elvis: Int = list?.size ?: -1


    val npe: Int = list!!.size // throw NullPointException if list is null
}

fun main(args: Array<String>) {
    counter = -1
    println(counter)
    counter = 10
    println(counter)
    counter = 5
    println(counter)
    counter = -5
    println(counter)
}

