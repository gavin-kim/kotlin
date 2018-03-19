package kotlinWorld.basics

import java.nio.charset.Charset
import java.security.cert.X509Extension
import java.util.function.ToDoubleFunction
import java.util.function.ToLongBiFunction
import javax.print.StreamPrintServiceFactory

/**
 * Class layout
 *
 * 1. Property declarations and initializer blocks
 * 2. Secondary constructors
 * 3. functions
 * 4. companion object
 */
class ClassLayoutExample(val name: String, var age: Int) {

    var sex: Char

    init {
        this.sex = 'M'
    }

    constructor(name: String, age: Int, sex: Char) : this(name, age) {
        this.sex = sex
    }

    fun firstFunction() {
        /**
         * Put related functions together,
         * so that someone reading the class from top to bottom would be able to follow the logic
         *
         * Put overload functions next to each other
         *
         * Keep the implementing members in the same order as members of the interface
         */
    }

    class ClassUsedInFirstFunction {
        /** Put nested classes next to the code that uses those classes */
    }

    companion object {
        // companion object

    }

    class ClassUsedOutsideOfThisClass {
        /** Put nested classes used externally after the companion object */
    }
}

/** For factory functions */
abstract class Foo {}
class FooImpl : Foo() {

    fun Foo(): Foo {
        return FooImpl()
    }
}

/** Names for test methods */
fun `test something`() {}
fun testSomething() {}


/** Top-level or object val properties use uppercase and underscore */
const val MAX_COUNT = 10
val USER_NAME_FIELD = "object val with no custom getter"

/** Top-level or object mutable */
val mutableCollection: MutableSet<String> = HashSet()

/** Reference to singleton objects */
val PersonComparator: Comparator<Foo> = java.util.Comparator { o1, o2 -> 0}

/** enum constans */
enum class Color {
    RED,
    BLUE,
    GREEN
}

/** Backing properties */
class BackingProperty {
    private val _elements = mutableListOf<Foo>() // For an implementation detail

    val elements: List<Foo> // For a public API
        get() = _elements
}

/**
 * Do NOT put a space before an opening parenthesis
 *
 * Never put a space after (, [
 *                   before ], )
 *
 * Put a space after // This is a comment
 */
class Hong(val x: Int) {
    fun foo(x: Int) {}
    fun bar() {
        foo(1)
    }
}

/**
 * Put a space before ':'
 */
interface IYa
abstract class Ya(x: String) : IYa { // 1. When it's used to separate a type and a supertype
    abstract fun ya(a: Int): Int
}
class YaImpl : Ya {
    constructor(x: String) : super(x) { // 2. When delegating to a superclass constructor or this(x)
        // constructor
    }

    override fun ya(a: Int): Int {
        return 0
    }

    val x = object : IYa { // 3. After object keyword

    }
}

/** Class header formatting */
class ClassHeaderForFewArgs(id: Int, name: String)
class ClassHeaderForManyArgs(
    id: Int,
    name: String,
    surname: String
) : IYa {
    // ...
}

/** For classes with a long supertypes **/
abstract class ClassHeaderForManyInterfaces :
    X509Extension,
    ToDoubleFunction<Double>,
    ToLongBiFunction<Int, Int> {

    override fun getCriticalExtensionOIDs(): MutableSet<String> {
        TODO("not implemented")
    }

    override fun getNonCriticalExtensionOIDs(): MutableSet<String> {
        TODO("not implemented")
    }

    override fun getExtensionValue(oid: String?): ByteArray {
        TODO("not implemented")
    }

    override fun hasUnsupportedCriticalExtension(): Boolean {
        TODO("not implemented")
    }
}

/** If the function signature doesn't fit on a single line */
fun reallyLongMethodName(
    reallyLongArgumentName: ReallyLongArgumentType,
    reallyLongArgumentName2: ReallyLongArgumentType
): StreamPrintServiceFactory? {
    return null
}
interface ReallyLongArgumentType

/** Prefer using an expression body for functions with single expression */
fun badFunctionWithSingleExpression(): Int { // BAD
    return 1
}
fun goodFunctionWithSingExpression(): Int = 1 // GOOD


/** Expression body formatting */
fun expressionBodyFormat(x: String) =
    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

/** For more complex properties, always put get() and set() on separate lines */
class Fooo {
    var foo: String = "foo"
        get() {
            return "foo"
        }

    private val defaultCharset: Charset? =
        Charset.defaultCharset() // something very long
}

fun formattingControlFlowStatements() {

    val value = "string"

    if (value.length > 10 ||
        value.endsWith("g")
    ) { // Put the closing parentheses of the condition together with the opening curly brace

    }
}

fun formattingWhenStatement(value: String) {

    val result = when (value) {
        "Soju" ->
            "I like it"

        "Vodka" ->
            "I love it"

        else ->
            "I don't know"
    }
}

/** Method call formatting */
fun methodCallFormatting() {
    drawSquare(
        x = 100, y = 200,
        width = 300, height = 900,
        fill = false
    )
}
fun drawSquare(width: Int, height: Int, x: Int, y: Int, fill: Boolean) = null

/** Lambda formatting */
fun lambdaFormatting() {
    listOf(1, 2, 3).filter { it > 10 } // Spaces should be used around the curly braces

    fun foo() {
        val ints = listOf(1, 2, 3, 4, 5, 6, 7)

        // No space between the label and opening curly brace
        ints.forEach lit@{

        }
    }
}

/**
 * Collection, List, Set, Map: Immutable collections
 * HashSet, ArrayList...     : Mutable collections
 */

// Bad: use of mutable collection type for value which will not be mutated
fun validateValue(actualValue: String, allowedValues: HashSet<String>) {}

// Good: immutable collection type used instead
fun validateValue(actualValue: String, allowedValues: Set<String>) {}

// Bad: arrayListOf() returns ArrayList<T>, which is a mutable collection type
val allowedValues = arrayListOf("a", "b", "c")

// Good: listOf() returns List<T>
val allowedValues2 = listOf("a", "b", "c")


/**
 * Default parameter values
 */
fun defaultParameterValues(a: String = "a") {}


/**
 * TODO: Start Again from Type aliases
 */

fun main(args: Array<String>) {

}
