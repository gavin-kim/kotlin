package test.kotlin.timePeriod

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import timePeriod.SimpleTimePeriod
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class TimePeriodHelperTest {

    @Test(expected = IllegalStateException::class)
    fun `toStringTimes - Invalid time period format 1`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{HH:mm:ss} - }")
    }

    @Test(expected = IllegalStateException::class)
    fun `toStringTimes - Invalid time period format 2`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{ - {HH:mm:ss}")
    }

    @Test(expected = IllegalStateException::class)
    fun `toStringTimes - Invalid time period format 3`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{HH:mm:ss}")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toStringTimes - Invalid date format 1`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{HH:mm:ss} - {abc}")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toStringTimes - Invalid date format 2`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{{HH:mm:ss} - {abc}}")
    }

    @Test
    fun `toStringTimes - Valid format 1`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        val stringTimePeriod = TimePeriodHelper.toStringTimes(timePeriod, "{HH:mm:ss} - {yyyy-MM-dd} {}{}{}")

        assertThat(stringTimePeriod, `is`("09:00:00 - 2000-01-01 {}{}{}"))
    }

    @Test
    fun `toStringTimes - Valid format 2`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:00")
        val stringTimePeriod = TimePeriodHelper.toStringTimes(timePeriod, "[{HH:mm:ss} - {yyyy-MM-dd}]")

        assertThat(stringTimePeriod, `is`("[09:00:00 - 2000-01-01]"))
    }
}