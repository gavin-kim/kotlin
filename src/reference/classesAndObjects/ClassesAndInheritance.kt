package reference.classesAndObjects

/** Constructors */
class PrimaryConstructor(name: String) {

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


