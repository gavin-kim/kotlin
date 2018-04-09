package feature

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * The Kotlin compiler does extensive static analysis to provide warnings and reduce boilerplate.
 * One of the most notable features is smartcasts.
 *
 * Contracts allow a function to explicitly describe its behavior in a way which is understood by the compiler.
 * */

fun noSmartCast(s: String?) {
    if (s.isNullOrEmpty())
        s?.length // no smartcast
}

@ExperimentalContracts
fun require(condition: Boolean) {
    contract {
        returns() implies condition
    }
    if (!condition) throw IllegalArgumentException()
}

@UseExperimental(ExperimentalContracts::class)
fun smartCastUsingContract(s: String?) {
    require(s is String)
    s.length // smartcast
}

@ExperimentalContracts
fun synchronize(lock: Any?, block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
}

@UseExperimental(ExperimentalContracts::class)
fun sychronizeUsingContract() {
    val x: Int
    val lock = "lock"
    synchronize(lock) {
        x = 42
    }
    x.compareTo(43)
}

