package reference.functionsAndLambdas

import reference.classesAndObjects.Bar
import reference.classesAndObjects.Foo
import reference.classesAndObjects.foo
import java.util.concurrent.locks.Lock

/**
 * Using higher-order function imposes certain runtime penalties,
 * each function is an object and it captures a closure
 *
 */
fun `inline function example`() {

    val param1 = "param1"
    val param2 = "param2"

    val l: Lock? = null

    lock(l!!) {
        param1 // lambda captures closures
        param2
        foo()
    }

    /**
     * Instead of creating a function object for the parameter and generating a call,
     * Inline function will be compiled like this
     * */
    l.lock()
    try {
        param1 // inline function use parameters just normal way
        param2
        foo()
    }
    finally {
        l.unlock()
    }
}

/**
 * inline modifier affects both the function itself and the lambdas passed to it
 * */
inline fun <T> lock(lock: Lock, body: () -> T): T {
    return TODO()
}

/**
 * compiler warning NOTHING TO INLINE
 */
inline fun `nothing to inline`(num: Int, str: String) {}

/**
 * noinline
 *
 * In case you want only some of the lambdas passed to an inline function to be inlined
 */
inline fun `inline and noinline`(inlined: () -> Unit, noinline noInlined: () -> Unit) {}

/**
 * Non-local return
 */
fun `non-locl return`() {

    /** Non-local return example */
    fun hasZeros(ints: List<Int>): Boolean {
        ints.forEach {
            if (it == 0) return true /** returns from hasZero (non-local return) */
        }
        return false
    }

    /** Non-local return in lambda */
    `inline and noinline`({
        /** inline function can make `inline and noinline` function return */
        return
    }, {
        /** noinline function can't make it */
        // return
    })
}

/**
 * crossinline: Used for nested function's param
 */
inline fun `crossinline function`(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body() // When inline function is used for parameters
        /** body function may have non-local return */
    }
}

fun `reified type parameters`() {
    fun <T> findParentOfType(clazz: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return 1 as T
    }

    findParentOfType(String::class.java)  // normal
    findParentOfType<String>()            // reified type parameter
}

/**
 * only type parameters of inline function can be reified
 * Since the function is inlined. no reflection is needed.
 * normal operator 'is' and 'as' are working now
 * */
inline fun <reified T> findParentOfType(): T {
    return 1 as T
}

inline fun <reified T> membersOf() = T::class.members

fun main(args: Array<String>) {
    membersOf<StringBuilder>() // return all properties and functions
}

/**
 * inline can be used on accessors of properties that don't have a backing field
 */
class InlineProperties {

    val foo: Foo
        inline get() = Foo()

    var bar: Bar
        get() = Bar()
        inline set(value) {}

    inline var fooBar: FooBar
        get() = FooBar()
        set(value) {}

    class FooBar
}

class PublicApi {

    /**
     * inline Public API function can not access non-public API function (i.e. private and internal)
     *
     * This imposes certain risks of binary incompatibility
     */
    inline fun publicApifunction(inlined: () -> Unit) {
        // privateFunction()
        internalFunction()  // only internal function can be used with @PublishedAPI annotation
        protectedFunction() // deprecated
    }


    private fun privateFunction() {

    }

    protected fun protectedFunction() {

    }

    @PublishedApi
    internal fun internalFunction() {

    }
}