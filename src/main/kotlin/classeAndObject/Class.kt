package classeAndObject

/** Constructors */
class PrimaryConstructor(name: String) { // name exists only during initializing

    val firstProperty: String = "firstProperty"

    init {
        println("First initializer")
    }

    val secondProperty: Int = 0

    init {
        println("Second initializer")
    }

    constructor(name: String, age: Int) : this(name) {
        println("Secondary constructor")
        println("All init blocks are parts of primary constructor")
    }

}

/** Constructor with annotation and visibility modifier */
class Customer @Inject public constructor(name: String) {

}
annotation class Inject


/** Private primary constructor */
class DontCreateMe private constructor() {

}


/** Primary constructor with derault value */
class PrimaryConstructorWithDefaultValue(val value: String = "default value") {

}

/** A member marked override is itself open. Use final to prohibit overriding */
open class ProhibitOverriding(): Base() {
    final override fun overrideMe() {
        super.overrideMe()
    }
}
open class Base() {
    open fun overrideMe() {

    }
}

/** Overriding Properties */
open class Foo {
    open val x: String
        get() = "open properties can be overridden"

    open val y: Number = 0
}
class Bar : Foo() {
    override val x: String
        get() = "Override inherited variable x"

    override val y: Int = 0 /** Subtype can be overridden */
}

/** You can override val -> var but not var -> val */
class Bar2(override val count: Int) : Foo2
class Bar3(override var count: Int = 0) : Foo2
interface Foo2 {
    val count: Int
}


/**
 * You should avoid using open members in the constructors, property initializer and init{}
 */
open class Parent(val name: String) {

    // #2
    init {
        println("init{} in Parent")
    }

    // #3
    open val size: Int =
        name.length.also {
            println("Initializing size in Parent: $it")
        }
}

class Child(name: String, val lastName: String) : Parent(name.capitalize().also { // #1 open member is used
    println("Argument for Parent: $it")
}) {

    // #4
    init {
        println("init{} in Child")
    }

    // #5 open member is used
    override val size: Int =
        (super.size + lastName.length).also {
            println("Initializing size in Child: $it")
        }
}



/** Calling the superclass implementation */
open class Rectangle {

    open fun rec() {
        println("Rectangle.rec()")
    }

    open val x: Int
        get() = 1
}

class Square : Rectangle() {

    override fun rec() {
        super.rec()
    }

    override val x: Int
        get() = 0

    inner class Point {

        fun p() {
            super@Square.rec() // Call rec() in a parent of Square
            println(super@Square.x)
            this.p()      // this is Point
            this@Square.x // Square's x
        }
    }
}


/** Override Rules */
open class A {
    open fun f() { println("A") }
    fun a() { println("a") }
}

interface B {
    fun f() { print("B") }
    fun b() { print("b") }
}

class C : A(), B {

    /** f() is duplicate. It must be overridden */
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}

fun main(args: Array<String>) {
    Child("Gavin", "Kim")
}

