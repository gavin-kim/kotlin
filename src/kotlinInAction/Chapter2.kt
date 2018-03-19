@file:JvmName("Chapter2Kt")

package kotlinInAction

import java.io.BufferedReader
import java.io.StringReader


/**
 * Statement: is always a top-level element in its enclosing block
 *            (for, do, do/while)
 *
 * Expression: has a value, which can be used as part of another expression
 */


/**
 *  "Block body" vs "Expression body" function
 */
fun blockBody(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun expressionBody(a: Int, b: Int): Int = if (a > b) a else b

/**
 * "Type Inference" Non return Type is only allowed for "Expression body"
 */
fun max(a: Int, b: Int) = if (a > b) a else b

/**
 * Using 'in' operator to check whether a value is in a range
 * Range expression can be apply by implementing java.lang.Comparable interface
 */
fun isLetter(c: Char): Boolean = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char): Boolean = c !in '0'..'9'
fun recognize(c: Char) =
    when (c) {
        in '0'..'9' ->
            "It's a digit!"

        in 'a'..'z',
        in 'A'..'Z' ->
            "It's a letter!"

        else -> throw Exception()
    }

class Person(val name: String, var age: Int)

class Rectangle(var height: Int, var width: Int) {
    val isSquare: Boolean
        get() = height == width
}

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}")
    }

    val person = Person("Gavin", 18)
    person.age = 20
    println("Name: ${person.name}, Age: ${person.age}") // String templates ${}

    val rect = Rectangle(2, 3)

    println("IsSquare: ${rect.isSquare}")

    rect.height = 3
    println("IsSquare: ${rect.isSquare}")

    println(Color.mix(Color.YELLOW, Color.RED))


    /**
     *  Smart Cast
     *  When a type is checked by using 'is' you don't need to cast it  afterward
     *  'Smart Cast' works only if a variable couldn't have changed
     */
    fun eval(expr: Expr): Int =
        when (expr) {
            is Num ->
                (expr as Num).value
            is Sum ->
                eval(expr.left) + eval(expr.right)
            else ->
                throw IllegalArgumentException("Unknown expression")
        }

    println(eval(Sum(Num(1), Sum(Num(2), Num(4)))))


    /** forloop Range */
    for (i in 11..20) {
        println(i)
    }
    /** forloop Counting backward from 100 */
    for (i in 200 downTo 100 step 20) {
        println(i)
    }
    /**
     * for (x in 0 until size) == for (x in 0..size-1)
     */
    val list = listOf(0)
    for (i in 0 until list.size) {
        println(list[i])
    }
    for ((index, element) in list.withIndex()) {
        println("Index: $index, Value: $element")
    }

    /**
     * 'try' as an expression
     */
    fun readNumber(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (ex: NumberFormatException) {
            return
        }

        println(number)
    }

    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}


/**
 * private doesn't have getter and setter
 */
enum class Color(private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b

    companion object {
        fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO

            else -> throw Exception("Dirty color")
        }

        fun betterMix(c1: Color, c2: Color) = when {
            (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
            (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
            (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO

            else -> throw Exception("Dirty color")
        }

        fun getWarmth(color: Color) = when (color) {
            Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
            Color.GREEN -> "neutral"
            Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
        }

    }

}

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
