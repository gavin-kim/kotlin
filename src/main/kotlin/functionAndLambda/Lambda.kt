package functionAndLambda

import classeAndObject.Outer

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
 * 3. Constructor kotlin
 * 4. Function kotlin
 * 5. Property kotlin
 * 6. Instance of custom class
 */

fun `instantiate a function type using lambda and anonymous function`() {

    val lambda: (Int) -> String = { a -> "${a * a}" }

    val anonymousFunction = fun(a: Int): String = "${a * a}"

    val constructorReference: (String) -> Regex = ::Regex
    val boundConstructorReference: (Outer) -> (Outer.Inner) = Outer::Inner

    val functionReference: (Int) -> String = Int::toString
    val boundCallableReference: (String) -> Boolean = "\\d+".toRegex()::matches

    val propertyReference: (List<Int>) -> Int = List<Int>::size

    val instanceOfCustomClass: (Int) -> Int = InstanceOfCustomClass()
}

class Outer {
    inner class Inner
    class Nested
}

class InstanceOfCustomClass : (Int) -> Int {
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

/**
 * Last parameter can be a lambda expression
 *
 * return in a lambda expression is return from the enclosing function
 */
fun `last parameter can be a lambda expression`() {
    val product = listOf(1, 2, 3).fold(1) { acc, e -> acc * e }
}

/**
 * Anonymous function can be a parameter
 */
fun `anonymous functions can be a parameter`() {

    val anonymousFunction = fun(x: Int, y: Int): Int = x + y

    val anonymousFunction2 = fun(x: Int, y: Int): Int {
        return x + y
        /** return in the anonymous function */
    }

    val filtered = listOf(1, 2, 3).filter(fun(item) = item > 0)
}

/**
 * Anonymous and Lambda expression can access its closure
 */
fun `Anonymous and Lambda expression can access its closure`() {
    var sum = 0
    (1..100).filter { it > 0 }.forEach {
        sum += it
    }
}

fun `Function literals with receiver`() {

    val sum: Int.(Int) -> Int = { other -> plus(other) }
    sum(2, 2)

    val sum2 = fun Int.(other: Int): Int = this + other
    sum2(2, 2)
}

/**
 * Lambda expressions can be used as function literals with receiver
 *
 * Type-safe builder example
 */
fun `Lambda expressions can be used as function literals with receiver`() {

    // HTML.() -> Unit   is a function type with receiver
    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML() // create the receiver object
        html.init() // pass the receiver object to the lambda
        return html
    }

    // type-safe builder
    html {
        head {
            // init function
            title { +"XML encoding with Kotlin" }

            /** head { } Head.() -> Unit is not allowed by @DslMarker */
            // head { }
        }

        body {
            // init function
        }
    }
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

/** After this annotation is added, Kotlin compiler knows which implicit receivers are part of the same DSL */
@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, "$indent  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class HTML : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)
    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body : BodyTag("body")
class B : BodyTag("b")
class P : BodyTag("p")
class H1 : BodyTag("h1")

class A : BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}


fun main(args: Array<String>) {
    val func = `function types can be combined using parentheses`()
    func(1)(2)


    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val countMap = hashMapOf<Int, Int>()
        val inverse = Array<MutableList<Int>?>(nums.size + 1) { null }

        for (num in nums) {
            countMap[num] = countMap[num]?.plus(1) ?: 1
        }

        for ((num, count) in countMap) {
            if (inverse[count] === null) {
                inverse[count] = mutableListOf()
            }
            inverse[count]!!.add(num)
        }

        return inverse.filterNotNull()
            .flatMap { it -> it }
            .takeLast(k)
    }

    println(topKFrequent(intArrayOf(1, 2), 2))

    val map = hashMapOf(
        if (1 == 1) {
            "asdf" to "asdf"
        } else {
            "ddd" to "ddd"
        }
    )

    println(map["asdf"])
    println(map["ss"])

}