package classeAndObject

import java.util.*


interface Printable {
    fun print()
}

class Printer(val x: Int) : Printable {
    override fun print() {
        println(x)
    }
}

/**
 * Delegation pattern
 *
 * Compiler will generate all the method of Printable in MultiPrinter
 * Compiler actually uses override fun print()
 */
class MultiPrinter(p: Printable) : Printable by p


fun main(args: Array<String>) {

    fun reverse(x: Int): Long {
        val list = LinkedList<Int>()

        var a = x
        while (a != 0) {
            list.addFirst(a % 10)
            a /= 10
        }

        var result = 0L
        var i = 1L

        for (y in list) {
            result += y * i
            i *= 10
        }

        return if (result > Int.MAX_VALUE) 0 else result
    }

    println(reverse(-123))

    val saveResultDTO = validateBeforeInsertMR(Rotation(1))
    println(saveResultDTO.messages)

    val saveResultDTO2 = validateBeforeInsertOTS(Rotation(3))
    println(saveResultDTO2.messages)



}

fun validateBeforeInsertMR(rotation: Rotation): SaveResultDTO {
    val validators = listOf(
        jobValidator,
        typeValidator,
        nameValidator
    )
    return invokeValidators(rotation, validators)
}

fun validateBeforeInsertOTS(rotation: Rotation): SaveResultDTO {
    val validators = listOf(typeValidator, nameValidator)
    return invokeValidators(rotation, validators)
}

fun invokeValidators(
    rotation: Rotation,
    validators: List<Validator<Rotation, ValidationMessage>>
): SaveResultDTO {

    val saveResultDTO = SaveResultDTO(mutableListOf())

    validators.forEach({ validator ->

        val validationMessage = validator.invoke(rotation)

        when (validationMessage) {
            is ErrorMessage -> {
                saveResultDTO.messages.add(validationMessage)
                return saveResultDTO
            }
            is AckMessage -> {
                saveResultDTO.messages.add(validationMessage)
            }
        }
    })
    return saveResultDTO
}

typealias Validator<IN, OUT> = (IN) -> OUT

val jobValidator: Validator<Rotation, ValidationMessage> = { rotation ->
    AckMessage("Do you wanna go ahead?", 1000)
}

val nameValidator: Validator<Rotation, ValidationMessage> = { rotation ->
    ErrorMessage("Name is exist")

}
val typeValidator: Validator<Rotation, ValidationMessage> = { rotation ->
    EmptyMessage
}

sealed class ValidationMessage
data class ErrorMessage(val message: String) : ValidationMessage()
data class AckMessage(val message: String, val ackCode: Int) : ValidationMessage()
object EmptyMessage : ValidationMessage()


data class Rotation(val rotId: Long)
data class SaveResultDTO(val messages: MutableList<ValidationMessage>)