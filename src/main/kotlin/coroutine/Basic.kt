package coroutine

import kotlinx.coroutines.*

fun main() {
    scopeBuilder()
}

fun block() {
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    runBlocking { // blocks the main thread until this scope completes
        delay(2000L)
    }
}

fun job() = runBlocking { // start main coroutine

    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }

    println("Hello,") // main coroutine continues here immediately
    delay(2000L) // delaying for 2 secs to keep JVM alive

    val job = GlobalScope.launch {
        delay(1000L)
        println("Gavin!")
    }

    println("Hello,")
    job.join() // wait until child coroutine (job) completes
}

fun scopeBuilder() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // create a new coroutine
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch
    }

    println("Coroutine scope is over") // This line is not printed until coroutine scope completes
}