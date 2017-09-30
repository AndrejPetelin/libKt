package Tests

import DateTime.*

/**
 * Created by andrej on 30.9.2017
 */

fun main(args: Array<String>) {
    val valid = DateTime(3, 5, 2017, 22, 30)

    println(valid.date.isLeapYear())
    println("${valid.day}, ${valid.month}, ${valid.year} - ${valid.hour}:${valid.minute}:${valid.second}")

    try {
        val invalid = DateTime(13, 14, 2018, 12, 61)
    } catch (e: Exception) {
        println("this doesn't go! " + e.message)
    }
}