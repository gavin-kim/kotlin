/**
 * @JvmName
 * this file name will be used in Java (kotlinInAction.StringFunctions)
 */
@file:JvmName("StringFunctions")

package kotlinInAction

import strings.lastChar as last

/** import the same extension function using different name */

/**
 * Defining an calling functions
 */

fun main(args: Array<String>) {

    val set = setOf(1, 2, 3)
    val list = listOf(1, 2, 3)
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")

    /** javaClass is equivalent of Java's getClass() */
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    println(joinToString(list))
    println(joinToString(list, postfix = "}"))

    println("Kotlin".last())

    val view: View = Button()
    view.click()

    println("Kotlin".lastChar)

    val stringBuilder: StringBuilder = StringBuilder("Kotlin")
    stringBuilder.lastChar = '0'
    println(stringBuilder.lastChar)

    println(listOf2("1", "2", "3"))

    val kotlinLogo = """| //
                       .|//
                       .|/ \""".trimMargin(".")

    println(kotlinLogo)

}

/**
 * Java doesn't have the concept of 'default parameter values'
 * @JvmOverloads annotation makes the compiler generate Java overloaded functions
 * This is imported in Java -> import static kotlinInAction.Chapter2Kt.joinToString;
 *
 * String joinToString(Collection<T> collection, String separator, String prefix, String postfix);
 * String joinToString(Collection<T> collection, String separator, String prefix);
 * String joinToString(Collection<T> collection, String separator);
 * String joinToString(Collection<T> collection);
 */
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "[",
    postfix: String = "]"
): String {
    val result = StringBuilder(prefix)

    for ((index, value) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(value)
    }

    return result.append(postfix).toString()
}

/**
 * Top-level(package level) properties
 *
 * Functions, properties can be placed at the top level of a file. Values are stored outside of a class
 */

var mutableValue = 0
/** getter and setter */
val readOnlyValue = 0
/** Runtime constant: getter */
const val CONSTANT_VALUE = 0
/** Compile-Time constant: No getter and setter */


/**
 *  Extension function
 *
 *  When an extension function is declared as a top-level function, so it's compiled to a static method
 *  A function can be called as a member of a class but it's defined outside of it.
 *  It doesn't matter whether String is written in Java, Kotlin or some other JVM language, such as Groovy.
 *  As long as it's compiled to a Java class, you can add your own extensions to that class
 *
 *  In the extension function, you can directly access the methods and properties of the class
 *  But it doesn't have access to private or protected members of the class
 */

fun String.lastChar(): Char = this[this.length - 1]
/**    ^                        ^    ^
 *  receiver type          receiver object
 *  (For extension)        (instance of the receiver type)
 */

/**
 *  Overriding doesn't apply to extension functions
 */
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

/**
 * If extension functions have the same name
 * A function is called depending on the static type of variable being declared, Not on the runtime type of the value stored
 *
 * val view: View = Button()
 * view.showOff() -> I'm a view!
 */
fun View.showOff() = println("I'm a view!")

fun Button.showOff() = println("I'm a button!")
fun Button.click() = println("New Button clicked")
/** Extension function can not override existing function */


/**
 * Extension properties
 */
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

/**
 * 'vararg' keyword: allows you to pass an arbitrary number fo values
 *
 * Spread operator '*': unpacks the array contents
 */
fun listOf2(vararg values: String): List<String> {
    return listOf("args: ", *values)
}

/**
 * infix call
 *
 * infix fun Any.to(other: Any) = Pair(this, other)
 */
val infixCall = mapOf(1 to "one", 2 to "two")
val reqularWay = mapOf(1.to("one"), 2.to("two"))

infix fun Double.powerOf(other: Double) = Math.pow(this, other)
infix fun Int.powerOf(other: Int) = Math.pow(this.toDouble(), other.toDouble())

val num = 2 powerOf 10


/** Destructuring declaration */
fun `destructuring declaration`() {
    val (number, name) = 1 to "one"
    println("number: $number, name: $name")

}

/** Kotlin split takes vararg for delimiters */
fun `Kotlin split takes vararg for delimiters`() {
    println("12.345-6.A".split('.', '-'))
}

/** /Usrs/yole/kotlin-book/chapter.adoc */
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/") // /Usrs/yole/kotlin-book
    val fullName = path.substringAfterLast("/")   // chapter.adoc

    val fileName = fullName.substringBeforeLast(".") // chapter
    val extension = fullName.substringAfterLast(".") // adoc

    /** In triple-quoted string you don't need to escape any characters */
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
    }
}

/** Local functions help you structure your code more cleanly and eliminate duplication */
data class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {

    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: $fieldName is empty")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")

    // Dao service
}

class ClassThatExtendsSubClassOfSealedClass(e1: NewExpr, e2: NewExpr) : Plus(e1, e2) {

}