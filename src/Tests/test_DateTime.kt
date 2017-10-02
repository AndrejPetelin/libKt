package Tests

import DateTime.*
import java.sql.Time
import java.time.LocalDateTime

/**
 * Created by andrej on 30.9.2017
 */

fun main(args: Array<String>) {
    val valid = DateTime(3, 5, 2017, 22, 30)

    println(valid.isLeapYear())
    println("${valid.day}, ${valid.month}, ${valid.year} - ${valid.hour}:${valid.minute}:${valid.second}")

    try {
        val invalid = DateTime(13, 14, 2018, 12, 61)
    } catch (e: Exception) {
        println("this doesn't go! " + e.message)
    }

    (1800..2017).map {
        if (Date(1, 1, it).isLeapYear()) println(it)
    }


    val t1 = DateTime(29, 4, 2017, 12, 12, 12)

    val t2 = DateTime(29, 3, 2017, 13, 12, 11)

    println(t1 < t2)

    val clock = System.currentTimeMillis() / 1000L

    val midnight = DateTime(30,9, 2017, 0, 0)

    val date = java.util.Date()

    println(date.toGMTString())
    println("system: ${date.time}, DateTime: ${midnight.toMilliSinceEpoch()}")
    println("difference: ${date.time - midnight.toMilliSinceEpoch()}")

    println(DateTime(toDate("02.10.2017"), toTime("14:05")).toString())
    println(DateTime(toDate("02.10.2017"), toTime("14:05:12")))
}
