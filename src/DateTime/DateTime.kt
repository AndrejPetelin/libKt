/**
 * Created by andrej on 30.9.2017
 */

package DateTime

class Time(val h: Int, val m: Int, val s: Int) {
    init {
        if (h < 0 || h > 24) throw Exception("DateTime - hour out of range")
        if (m < 0 || m > 60) throw Exception("DateTime - minute out of range")
        if (s < 0 || s > 60) throw Exception("DateTime - second out of range")
    }
}

class Date(val day: Int, val month: Int, val year: Int) {
    init {
        if (!isValid()) throw Exception("DateTime - invalid date")
    }
}

fun Date.isLeapYear(): Boolean {
    if (year % 4 != 0) return false
    if (year % 100 != 0) return true
    if (year % 400 != 0) return false
    return true
}

fun Date.isValid(): Boolean {
    if (day <= 0 || month <= 0) return false

    if (isLeapYear() && month == 2 && day <= 29) return true

    when (month) {
        1 -> if (day <= 31) return true
        2 -> if (day <= 28) return true
        3 -> if (day <= 31) return true
        4 -> if (day <= 30) return true
        5 -> if (day <= 31) return true
        6 -> if (day <= 30) return true
        7 -> if (day <= 31) return true
        8 -> if (day <= 31) return true
        9 -> if (day <= 30) return true
        10 -> if (day <= 31) return true
        11 -> if (day <= 30) return true
        12 -> if (day <= 31) return true
    }
    return false
}



class DateTime(day: Int, month: Int, year: Int, h: Int, m: Int, s: Int = 0) {
    val date = Date(day, month, year)
    val time = Time(h, m, s)

    val day = date.day
    val month = date.month
    val year = date.year
    val hour = time.h
    val minute = time.m
    val second = time.s

    override fun toString(): String {
        return "$day.$month.$year, $hour:$minute:$second"
    }
}

