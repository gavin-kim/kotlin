package reference.others


class Controller {
    private val _items = mutableListOf<String>()
    val items: List<String>
        get() = _items.toList() // outside read-only inside mutable
}

/**
 * Kotlin read-only collections are covariant
 *
 * <out T> == <? extends T> covariant
 * <in T> == <? super T> contravariant
 *
 * List<out T>
 * Set<out T>
 * Map<K, out V>
 *
 *
 *     fun get(): List<out T>  the function can return any subtype of T
 *     fun set(Some<in T>)     the function can take any supertype of T as a parameter
 *                             and take any subtype of T in the function
 */

open class Shape
class Rectangle: Shape()


fun main(args: Array<String>) {



    val rectangles = mutableListOf(Rectangle())

    // List<? extends Shape> = MutableList<Rectangle>
    val shapes: List<Shape> = rectangles
}