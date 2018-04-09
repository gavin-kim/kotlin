package reference.others

fun main(args: Array<String>) {

    /** Data class: data class automatically declare componentN() */

    data class Person(val name: String, val age: Int, val sex: Char)
    val (name, age) = Person("Kim", 20, 'M')
    println("$name, $age")


    /** For loop */
    val map = (1..100).zip('a'..'z')
    for ((a, b) in map) {
        println("$a, $b")
    }

    /**
     * { a -> ... }         one parameter
     * { a, b -> ...}       two parameters
     * { (a, b) -> ...}     a destructured pair
     * { (a, b), c -> ...}  a destructured pair and another parameter
     */
}