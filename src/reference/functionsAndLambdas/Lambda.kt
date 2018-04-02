package reference.functionsAndLambdas

import reference.classesAndObjects.Outer

/**
 * Higher-order function: A function that takes functions as parameters or returns a function
 *
 * */

fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial

    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }

    return accumulator
}

/** Return nullable function type */
fun `nullable function type return`(): ((Int, Int) -> Int)? {
    return { a, b -> a + b }
}

/**
 * Function types can be combined using parentheses
 *
 * The arrow notation is right-associative
 *
 * (Int) -> ((Int) -> Unit) equals (Int) -> (Int) -> Unit
 *
 * */
fun `function types can be combined using parentheses`(): (Int) -> ((Int) -> Int) {
    return when {
        "lamda" == "lambda" -> { a -> { b -> a + b } }
        else -> fun(a: Int): (Int) -> Int = { b -> a + b }
    }
}

fun `arrow notation is right-associative`(): ((Int) -> (Int)) -> Int {
    return { func -> func(0) }
}


/**
 * Instantiating a function type:
 *
 * 1. Lambda,
 * 2. Anonymous function
 * 3. Constructor reference
 * 4. Function reference
 * 5. Property reference
 * 6. Instance of custom class
 */

fun `instantiate a function type using lambda and anonymous function`() {

    val lambda: (Int) -> String = { a -> "${a * a}" }

    val anonymousFunction = fun(a: Int): String = "${a * a}"

    val constructorReference: (String) -> Regex = ::Regex
    val boundConstructorReference: (Outer) -> (Outer.Inner) = Outer::Inner

    val functionReference = Int::toString
    val boundCallableReference: (String) -> Boolean = "\\d+".toRegex()::matches

    val propertyReference: (List<Int>) -> Int = List<Int>::size

    val instanceOfCustomClass = InstanceOfCustomClass()
}

class Outer {
    inner class Inner
    class Nested
}

class InstanceOfCustomClass: (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}


/**
 * Function type with receiver               Function type
 * A.(B) -> C                  Can be          (A, B) -> C
 */
fun `function types are interchangeable`() {

    val repeat2: String.(Int) -> String = { times -> repeat(times) }
    val twoParameters: (String, Int) -> String = repeat2

    fun runTransformation(func: (String, Int) -> String): String {
        return func("Hello", 3)
    }

    println(runTransformation(repeat2))
}

/**
 * Invoke function type instance
 */
fun `invoke function type instance`() {

    val concat: (String, String) -> String = String::plus
    concat("Hello", "Hi")
    concat.invoke("Hello", "Hi")

    val plus: Int.(Int) -> Int = Int::plus
    1.plus(2)
    plus(1, 2)
    plus.invoke(1, 2)

    val matrix: (Int) -> (Int) -> Int = { a -> { b -> a * b } }
    matrix(3)(3)
    matrix.invoke(3).invoke(3)
}



fun main(args: Array<String>) {
    val func = `function types can be combined using parentheses`()
    func(1)(2)
}