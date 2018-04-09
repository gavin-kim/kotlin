package classeAndObject


/**
 * Sealed classes are used for restricted class hierarchies
 *
 * Enum class   : The set of values for an enum type is restricted.
 *                Enum constants exist only as a single instance.
 *
 * Sealed class : The set of subclasses for sealed class is restricted
 *                A subclass can have many instances and must be placed in the same file.
 */

/** A sealed class is abstract by itself. It cannot have non-private constructors */
sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
open class CanHaveSubClass(open val number: Int) : Expr()
object NotANumber : Expr()


/** A Subclass of a subclasse of sealed class can be placed any others files */
class SubClass(override val number: Int) : CanHaveSubClass(number)

fun main(args: Array<String>) {

    /** Key benefits of using sealed classes */
    fun eval(expr: Expr): Int = when (expr) {
        is Const -> expr.number
        is CanHaveSubClass -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> 0
    }
}