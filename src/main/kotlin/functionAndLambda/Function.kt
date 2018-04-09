package functionAndLambda

/**
 * Defaults arguments
 */

fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

}

/**
 * Overridden function can not have default value for parameters
 */
open class BB : AA() {
    override fun foo(i: Int) {

    }
}

open class AA {
    open fun foo(i: Int = 10) {

    }
}

fun `Last argument lambda`() {

    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {

    }

    foo {println()}
    foo(1) { println("hello") }
    foo(1, 2) { println("hello") }
}

/** Infix notation */
infix fun Int.equals(other: Int) : Boolean {
    return this == other
}

fun `Infix notation test`() {
    println(1 equals 2)

    /** Infix function calls have lower precedence than the arithmetic operators */
    println(1 shl 2 + 3)
    println(1 shl (2 + 3))

    val n = 10
    println(0 until n * 2)
    println(0 until (n * 2))

    println(listOf(1, 2, 3) union setOf(3, 4, 5) as Set<*>)
    println(listOf(1, 2, 3) union (setOf(3, 4, 5) as Set<*>))


    /** Infix function calls have higher precedence than (&&, ||, is, in) */

    val a = true
    val b = false
    val c = false

    println(a && b xor c)
    println(a && (b xor c))

    val d = listOf(true, true)
    println(a xor b in d)
    println((a xor b) in d)
}

fun fibonacci(n: Int): Int {
    tailrec fun fib(prev: Int, curr: Int, count: Int): Int {
        return if (count == n) curr else fib(curr, prev + curr, count + 1)
    }

    return when {
        n <= 1 -> 0
        n == 2 -> 1
        else -> fib(0, 1, 2)
    }
}


fun main(args: Array<String>) {
    `Infix notation test`()
    println(fibonacci(1000))
}
