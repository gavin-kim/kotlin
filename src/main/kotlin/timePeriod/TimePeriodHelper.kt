
import timePeriod.TimePeriod
import java.text.SimpleDateFormat

private const val DEFAULT_TIME_PERIOD_FORMAT = "{HH:mm:ss}-{HH:mm:ss}"
private const val DATE_PATTERN = "[{][^}]*[}]"

object TimePeriodHelper {

    fun toStringTimes(
        timePeriod: TimePeriod,
        format: String = DEFAULT_TIME_PERIOD_FORMAT
    ): String {
        val regex = Regex(DATE_PATTERN)
        val matchResults = regex.findAll(format)

        check(matchResults.count() >= 2) { "Invalid TimePeriod String format: $format"}

        val startDateFormat = SimpleDateFormat(matchResults.elementAt(0).value.removeSurrounding("{", "}"))
        val endDateFormat = SimpleDateFormat(matchResults.elementAt(1).value.removeSurrounding("{", "}"))

        return format
            .replaceFirst(regex, startDateFormat.format(timePeriod.startDate))
            .replaceFirst(regex, endDateFormat.format((timePeriod.endDate)))
    }
}