
import timePeriod.OverlapType
import timePeriod.SimpleTimePeriod
import timePeriod.TimePeriod
import java.text.SimpleDateFormat
import java.time.Period
import java.util.*

private const val DEFAULT_TIME_PERIOD_FORMAT = "@start{HH:mm:ss} - @end{HH:mm:ss}"
private const val START_DATE_PATTERN = "@start\\{[^}]*[}]"
private const val END_DATE_PATTERN = "@end\\{[^}]*[}]"
private const val COMMA = ","
private const val SYNC_YEAR = 2000
private const val SYNC_MONTH = 0
private const val SYNC_DATE = 10

object TimePeriodHelper {

    fun toStringTimes(
        timePeriod: TimePeriod,
        format: String = DEFAULT_TIME_PERIOD_FORMAT
    ): String {
        val startDateRegex = Regex(START_DATE_PATTERN)
        val endDateRegex = Regex(END_DATE_PATTERN)

        val result = startDateRegex.replace(format) {
            val dateFormat = SimpleDateFormat(it.value.removeSurrounding("@start{", "}"))
            dateFormat.format(timePeriod.startDate)
        }

        return endDateRegex.replace(result) {
            val dateFormat = SimpleDateFormat(it.value.removeSurrounding("@end{", "}"))
            dateFormat.format(timePeriod.endDate)
        }
    }

    /**
     *  @return time period as string milliseconds with separator
     */
    fun toMilliSeconds(timePeriod: TimePeriod): String {
        return "${timePeriod.startDate.time}$COMMA${timePeriod.endDate.time}"
    }

    /**
     * Parses string milliseconds (1113796800000,1224796800000) to time period
     */
    fun parseStringMilliseconds(milliseconds: String): TimePeriod {
        val tokens = milliseconds.split(",").map { it.trim().toLong() }
        return SimpleTimePeriod(Date(tokens[0]), Date(tokens[1]))
    }

    fun doOverlap(a: TimePeriod, b: TimePeriod, contiguousIsOverlap: Boolean = true): Boolean {
        return if (contiguousIsOverlap) {
            a.startDate <= b.endDate && b.startDate <= a.endDate
        } else {
            a.startDate < b.endDate && b.startDate < a.endDate
        }
    }

    fun doOverlap(actor: TimePeriod, targets: Collection<TimePeriod>, contiguousIsOverlap: Boolean = true): Boolean {
        return targets.any { TimePeriodHelper.doOverlap(actor, it, contiguousIsOverlap) }
    }

    fun getOverlapType(actor: TimePeriod, target: TimePeriod): OverlapType {
        return when {
            actor.endDate < target.startDate || target.endDate < actor.startDate -> OverlapType.NONE
            actor.startDate == target.startDate && actor.endDate == target.endDate -> OverlapType.EXACT
            target.startDate < actor.startDate && actor.endDate < target.endDate -> OverlapType.INNER
            target.startDate < actor.startDate -> OverlapType.RIGHT
            actor.endDate < target.endDate -> OverlapType.LEFT
            else -> OverlapType.OUTER
        }
    }

    fun getOverlapTypeOnlyForTime(actor: TimePeriod, target: TimePeriod): OverlapType {
        actor.startDate.
    }

    fun syncDates(timePeriod: TimePeriod, shiftDates: Int) {
        val dateToSync = S
    }

    private infix fun Date.within(timePeriod: TimePeriod): Boolean {
        return timePeriod.startDate <= this && this <= timePeriod.endDate
    }
}
