package kotlinWorld.basics

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/** Function returning no meaningful value (Unit can be omitted) */
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

/** Return nullable value */
fun parseInt(str: String): Int? {
    return null
}

/** Read-only Collections */
fun readOnlyCollections() {
    val list = listOf(1, 2, 3)
    val map = mapOf(1 to "one", 2 to "two")
}

/** Mutable Collections */
fun mutableCollections() {
    val list = mutableListOf(1, 2, 3)
    list.add(3, 4)

    val map = mutableMapOf(1 to "one", 2 to "two")
    map[3] = "three"
}

/** Createing a singleton */
object SingletonClass {
    val name = "Name"
}

/** If not null and else shorthand */
fun ifNotNullAndElse() {
    val files = File("Test").listFiles()
    val sizeOrNull = files?.size   // It can be null or size

    println(sizeOrNull)
    println(sizeOrNull ?: "empty") // If sizeOrNull is null "empty"
}

/** Executing a statement if null */
fun executeStatementIfNull() {
    val emails: List<String> = emptyList()
    val mainEmail = emails.firstOrNull() // return first or null

    println(mainEmail)
    println(mainEmail ?: "empty") // if mailEmail is null return "empty"
    mainEmail ?: throw IllegalStateException()
}

/** Execute If not null */
fun executeIfNotNull() {

    fun transformValue(str: String): String? {
        return null
    }

    val value = null

    val transformedValue  = value?.let {
        println("value is not null")
        transformValue(it)
    } ?: "default value if value is null"

    println(transformedValue)
}

/** if expression */
fun ifExpression(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

/** builder-style usage of methods */
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply {
        fill(-1)
    }
}

/** Calling multiple methods on an object instance 'with' */
fun callingMultipleMehodsOnObjectInstance() {
    val myTurtle = Turtle()

    with(myTurtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}

/** Java7's try with resources */
fun tryWithResources() {
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

/** Consuming a nullable Boolean */
fun consumingNullableBoolean() {
    val b: Boolean? = null

    if (b == true) {

    } else {
        // b is false or null
    }
}

/**
 * Creating DTOs
 *
 * val: getters
 * var: getters and setters
 * equals()
 * hashCode()
 * toString()
 * copy()
 * component1(), component2(), ...
 *
 * */
data class ExampleDTO(val name: String, val email: String)

fun main(args: Array<String>) {
    tryWithResources()
}