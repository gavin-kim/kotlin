package reference.classesAndObjects

/**
 * Declaration-site variance
 *
 * variance annotation (in, out)
 * in: makes a type parameter contravariant (Consumer)
 * out: makes a type parameter covariant (Producer)
 *
 * */
interface Source<out T> { // <? extends T>
    fun nextT(): T // only used for output
}

fun covarianceDemo(source: Source<Type>) { // Source<out T> == Source<? extends Type>: covariant type

    val type: Type = source.nextT()
    val superType: SuperType = source.nextT() // Can produce supertypes of Type
    val superTypeSource: Source<SuperType> = source // So It's OK in Kotlin
}

interface Comparable2<in T> { // <? super T>
    operator fun compareTo(other: T): Int // only used for input
}

fun contravarianceDemo(comparable: Comparable2<Type>) { // Comparable<in Type> ==  Comparable2<? super Type>: contravariant type

    comparable.compareTo(Type())
    comparable.compareTo(SubType()) // Can consume subtypes of Type
    val subTypeComparable: Comparable2<SubType> = comparable // So It's OK in Kotlin
}

fun main(args: Array<String>) {

    /**
     * Use-site variance: Type projections
     */

    /**
     * <? extends Number> from is used for the Producer
     */
    fun copy(from: Array<out Number>, to: Array<Number>) {
        for ((index, number) in from.withIndex()) { /** from can produce subtypes of numbers */
            to[index] = number
        }
    }

    val array = Array<Number>(3, { _ -> 0 })
    copy(arrayOf(1.0, 2.0, 3.0), array) // Double type
    copy(arrayOf(1, 2, 3), array)       // Int type

    /**
     * <? super Number> : dest is used for the Consumer
     */
    fun fill(dest: Array<in Number>, value: Number) {
        dest.fill(value) /** dest can take subtypes of Number */
    }

    fill(array, 2.0) // Double
    fill(array, 1)   // Int
    fill(array, 2L)  // Long


    /**
     * Star-projections
     *
     * Function<*, *> means Function<in Nothing, out Any?>
     */
    fun copyFromStar(from: Array<*>, to: Array<Any?>) {
        for ((index, any) in from.withIndex()) {
            to[index] = any // star projection can produce a null
        }
    }

    val anyArray = Array<Any?>(3, { _ -> 0 })
    copyFromStar(arrayOf("123", 2, 3), anyArray)


    /** Upper bounds: colon is the upper bound */
    fun <T : Comparable<T>> sort(list: List<T>) {}

    sort(listOf(1, 2, 3)) // OK Int is a subtype of Comparable<Int>
    // sort(listOf(mapOf(1 to "one"))) Error HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>


    /** When the same type parameter needs more than one upper bound */
    fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
        return list.filter { it > threshold }
            .map { it.toString() }
    }
}

open class SuperType
open class Type : SuperType()
class SubType : Type()
