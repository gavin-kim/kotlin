package kotlinInAction

import java.io.Serializable
import java.lang.Math.random

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(isFocus: Boolean) = println("I ${if (isFocus) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

/**
 * Kotlin classes are 'final' is by default
 * If you want to allow the creation of subclassed of a class You need to mark the class with 'open'
 *
 * ---------------------------------------------------------------------
 * | final     | Can't be overridden (by default)
 * | open      | Can be overridden
 * | abstract  | Must be overridden  (only for abstract classes)
 * | override  | Overrides a member from a superclass (open by default)
 * ---------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------
 * | Modifier  | Class member             | Top-level declaration
 * ---------------------------------------------------------------------
 * | public    | visible everywhere       | visible everywhere
 * | internal  | visible in a module      | visible in a module
 * | protected | visible in subclasses    | --
 * | private   | visible in a class       | visible in a file
 * ---------------------------------------------------------------------
 */
open class Icon: Clickable, Focusable {

    /**
     * You must provide an explicit implementation if more than one implementation for the same member is inherited
     *
     * If you need only one inherited implementation: override fun showOff() = super<clickable>.showOff()
     * */
    final override fun showOff() { // final function
        super<Clickable>.showOff() // In Java Clickable.super.showOff()
        super<Focusable>.showOff()
    }

    open fun animate() { // open function
        println("I'm animating!")
    }

    fun hold() { // final function by default

    }

    override fun click() { // open function because of Overriding 'open' function
        println("I was clicked")
    }
}

class BigIcon: Icon() {

    val getSomeThing: Int
        get() = (random() * 100).toInt() // custom accessor

    override fun animate() {
        super.animate()
        println("I'm animating something more")
    }
}

abstract class Animated {

    abstract fun animate() // must be implemented

    open fun stopAnimating() { // open
    }

    fun animateTwice() { // final
    }
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
    fun bark() = println("Bark Bark!")
}

internal fun TalkativeButton.giveSpeech() { // internal receiver type
    /** Extension functions don't get access to its private or protected members */
    // this.yell()    // private
    // this.whisper() // protected
    this.bark()    // public
}

/**
 * ----------------------------------------------------------------------------------
 * | Class A declared within another class B       | In Java         | In Kotlin
 * ----------------------------------------------------------------------------------
 * | Nested class (No reference to an outer class) | static class A  | class A
 * | Inner class  (Reference to an outer class)    | class A         | inner class A
 * ----------------------------------------------------------------------------------
 *
 */
class Outer {
    class Nested {
        /** No reference to an outer class */
    }

    inner class Inner {
        val outer = this@Outer
    }
}

/**
 * Sealed Classes have subclass, but all of them must be declared in the same file as the sealed class itself
 */
sealed class NewExpr
data class Const(val number: Double) : NewExpr()
open class Plus(val e1: NewExpr, val e2: NewExpr) : NewExpr()
object NotANumber : NewExpr()

fun newEval(expr: NewExpr): Double = when(expr) {
    is Const -> expr.number
    is Plus -> newEval(expr.e1) + newEval(expr.e2)
    NotANumber -> Double.NaN
    // 'else' is not required because we've covered all the cases
}


fun main(args: Array<String>) {

    val icon = Icon()

    icon.showOff()
    icon.setFocus(true)
    icon.click()
}



