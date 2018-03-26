package reference.classesAndObjects


/**
 * Data class
 *
 * 1. Primary constructor must have at least one parameter
 * 2. All Primary constructor parameter must be 'val' or 'var'
 * 3. Data classes cannot be 'abstract', 'open', 'sealed' or 'inner'
 *
 * 4. If there are explicit implementations of equals(), hashCode() or toString() id data class body or
 *    'final' implementations in a superclass. these functions are not generated
 *
 * 5. If a superclass has the componentN() functions they must be 'open'
 * 6. Explicit implementation for componentN() and copy() functions is not allowed
 */
data class DataClass(val name: String, val age: Int) {

}

/**
 * Only 'name' property will be used for toString(), equals(), hashCode() and copy()
 * And there will be only one component function component1()
 *
 * DataClassDefaultValue("Kim") = DataClassDefaultValue("Kim") is treated as equal
 *
 */
data class DataClassDefaultValue(val name: String = "") {
    var age: Int = 0
}

fun main(args: Array<String>) {

    /** Copy some of properties */
    val jack = DataClass(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    /** Destructuring declaration */
    val (name, age) = jack
    println("$name, $age years of age")
}