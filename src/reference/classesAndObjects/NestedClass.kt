package reference.classesAndObjects

import java.util.function.Consumer

/**
 * ----------------------------------------------------------------------------------
 * | Class A declared within another class B       | In Java         | In Kotlin
 * ----------------------------------------------------------------------------------
 * | Nested class (No reference to an outer class) | static class A  | class A
 * | Inner class  (Reference to an outer class)    | class A         | inner class A
 * ----------------------------------------------------------------------------------
 */

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun foo() = bar
    }
}

fun main(args: Array<String>) {

    val nested = Outer.Nested()
    val inner = Outer().Inner()
}

/**
 * Anonymous inner class and Lambda for Java functional interfaces
 */
fun anonymousInnerClass() {
    addListener(object : Consumer<Int> {
        override fun accept(t: Int) {
            println(t)
        }
    })

    val listener = Consumer<Int> { t -> println(t) }
}

fun addListener(listener: Consumer<Int>) {

}
