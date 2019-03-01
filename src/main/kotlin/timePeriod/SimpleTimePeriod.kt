package timePeriod

import java.text.SimpleDateFormat
import java.util.*

data class SimpleTimePeriod(
    private var start: Date,
    private var end: Date
): TimePeriod {

    constructor(startDate: String, endDate: String): this(parse(startDate), parse(endDate))

    override fun getStartDate() = start
    override fun getEndDate() = end

    companion object {
        fun parse(date: String): Date {
            return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date)
        }
    }
}

