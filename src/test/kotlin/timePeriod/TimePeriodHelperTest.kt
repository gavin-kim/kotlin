package test.kotlin.timePeriod

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import timePeriod.SimpleTimePeriod
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class TimePeriodHelperTest {

    @Test(expected = IllegalArgumentException::class)
    fun `toStringTimes - Invalid format 1`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 17:00", "2000-01-02 01:00")
        TimePeriodHelper.toStringTimes(timePeriod, "@start{HH:mm:ss} - @end{abc}")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toStringTimes - Invalid format 2`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 17:00", "2000-01-02 01:00")
        TimePeriodHelper.toStringTimes(timePeriod, "{ @start{abc} - @end{yyyy-MM-dd} }")
    }

    @Test
    fun `toStringTimes - Valid format 1`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 17:00", "2000-01-02 01:00")
        val stringTimePeriod = TimePeriodHelper.toStringTimes(timePeriod, "@start{HH:mm:ss}} - @end{yyyy-MM-dd}}")

        assertThat(stringTimePeriod, `is`("17:00:00} - 2000-01-02}"))
    }

    @Test
    fun `toStringTimes - Valid format 2`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 17:00", "2000-01-02 01:00")
        val stringTimePeriod = TimePeriodHelper.toStringTimes(timePeriod, "[@end{HH:mm:ss} - @start{yyyy-MM-dd}]")

        assertThat(stringTimePeriod, `is`("[01:00:00 - 2000-01-01]"))
    }

    @Test
    fun `toStringTimes - Valid format 3`() {
        val timePeriod = SimpleTimePeriod("2000-01-01 17:00", "2000-01-02 01:00")
        val stringTimePeriod = TimePeriodHelper.toStringTimes(timePeriod, "[@end{yyyy-MM-dd} @end{HH:mm:ss}]")

        assertThat(stringTimePeriod, `is`("[2000-01-02 01:00:00]"))
    }
}