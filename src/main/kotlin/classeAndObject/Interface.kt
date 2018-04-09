package classeAndObject

interface AAA {
    fun foo() {
        println("A")
    }

    fun bar()
}

interface BBB {
    fun foo() {
        println("B")
    }

    fun bar() {
        println("bar")
    }
}

class CCC : AAA {
    override fun bar() {
        println("bar")
    }
}

class DDD : AAA, BBB {
    override fun foo() {
        super<AAA>.foo()
        super<BBB>.foo()
    }

    override fun bar() {
        super.bar()
    }
}