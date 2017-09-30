/**
 * DateTime.kt - simple tools for dealing with and calculating time and date
 *
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

    fun isLeapYear(): Boolean {
        if (year % 4 != 0) return false
        if (year % 100 != 0) return true
        if (year % 400 != 0) return false
        return true
    }

    fun isValid(): Boolean {
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
}


class DateTime(day: Int, month: Int, year: Int, h: Int, m: Int, s: Int = 0): Comparable<DateTime> {
    private val date = Date(day, month, year)
    private val time = Time(h, m, s)

    val day = date.day
    val month = date.month
    val year = date.year
    val hour = time.h
    val minute = time.m
    val second = time.s

    override fun toString() =
        "${String.format("%02d",day)}.${String.format("%02d", month)}.$year, $hour:${String.format("%02d", minute)}:${String.format("%02d",second)}"

    override fun compareTo(other: DateTime): Int {
        return when {
            year - other.year != 0 -> year - other.year
            month - other.month != 0 -> month - other.month
            day - other.day != 0 -> day - other.day
            hour - other.hour != 0 -> hour - other.hour
            minute - other.minute != 0 -> minute - other.minute
            else -> second - other.second
        }
    }
}

