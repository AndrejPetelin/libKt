/**
 * DateTime.kt - simple tools for dealing with and calculating time and date
 *
 * Created by andrej on 30.9.2017
 *
 * Date.isLeapYear() is modeled from https://www.mathsisfun.com/leap-years.html
 * DateTime.toMilliseconds() is modeled from http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap04.html#tag_04_15
 */

package DateTime


class Time(val hour: Int, val minute: Int, val second: Int = 0) {
    init {
        if (hour < 0 || hour >= 24) throw Exception("DateTime - hour out of range")
        if (minute < 0 || minute >= 60) throw Exception("DateTime - minute out of range")
        if (second < 0 || second >= 60) throw Exception("DateTime - second out of range")
    }
}

internal val daysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

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

    private fun isValid(): Boolean {
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


class DateTime(private val date: Date, private val time: Time): Comparable<DateTime> {
    constructor(day: Int, month: Int, year: Int, hr: Int, min: Int, sec: Int = 0): this(Date(day, month, year), Time(hr, min, sec))

    val day get() = date.day
    val month get() = date.month
    val year get() = date.year
    val hour get() = time.hour
    val minute get() = time.minute
    val second get() = time.second


    override fun toString() =
        "${String.format("%02d",day)}.${String.format("%02d", month)}.$year," +
                " $hour:${String.format("%02d", minute)}:${String.format("%02d", second)}"

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

    fun isLeapYear() = date.isLeapYear()

    private fun yday(): Int {
        var days = day
        for (m in 1 until month) days += daysInMonth[m - 1]
        if (isLeapYear()) ++days
        return days
    }

    /**
     * time since Epoch (01.01.1970 0:00:00) in seconds
     * note that the function does not take timezones into account
     */
    fun toSecsSinceEpoch(): Long {
        val y = (year - 1900).toLong()
        val d = yday().toLong() - 1
        return second + minute * 60 + hour * 3600 + d * 86400 +
                (y - 70) * 31536000 + ((y - 69) / 4) * 86400 -
                ((y - 1) / 100) * 86400 + ((y + 299) / 400) * 86400
    }

    /**
     * time since Epoch in milliseconds
     * note that the function does not take timezones into account
     */
    fun toMilliSinceEpoch() = toSecsSinceEpoch() * 1000
}

/**
 * converts string str to Date. String needs to be formatted as "dd.mm.yyyy". If any of values are out of range Date
 * will throw an Exception
 */
fun toDate(str: String): Date {
    val raw = str.split(".")
    return Date(raw[0].toInt(), raw[1].toInt(), raw[2].toInt())
}

/**
 * converts string str to Time. String needs to be formatted as "hh:mm:ss", where seconds are optional. If any values
 * are out of range Time will throw an Exception
 * TODO: what about milliseconds?
 */
fun toTime(str: String): Time {
    val raw = str.split(":")
    if (raw.size < 3) {
        return Time(raw[0].toInt(), raw[1].toInt(), 0)
    }
    else {
        return Time(raw[0].toInt(), raw[1].toInt(), raw[2].toInt())
    }
}

