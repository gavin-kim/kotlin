package test.kotlin.timePeriod

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import timePeriod.SimpleTimePeriod
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

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

    @Test
    fun `doOverlap - Inner`() {
        val actor = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 21:00")
        val target = SimpleTimePeriod("2000-01-01 00:00", "2000-01-02 00:00")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))
    }

    @Test
    fun `doOverlap - Outer`() {
        val actor = SimpleTimePeriod("2000-01-01 00:00", "2000-01-02 00:00")
        val target = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 21:00")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))
    }

    @Test
    fun `doOverlap - Total`() {
        val actor = SimpleTimePeriod("2000-01-01 00:00", "2000-01-02 00:00")
        val target = SimpleTimePeriod("2000-01-01 00:00", "2000-01-02 00:00")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))
    }

    @Test
    fun `doOverlap - Left`() {
        val actor = SimpleTimePeriod("2000-01-01 09:00", "2000-01-01 17:01")
        val target = SimpleTimePeriod("2000-01-01 17:00", "2000-01-01 20:00")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))
    }

    @Test
    fun `doOverlap - Right`() {
        val actor = SimpleTimePeriod("2000-01-01 02:00", "2000-01-01 03:00")
        val target = SimpleTimePeriod("2000-01-01 01:00", "2000-01-01 02:01")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))
    }

    @Test
    fun `doOverlap - No overlap`() {
        val actor = SimpleTimePeriod("2000-01-01 00:00", "2000-01-01 00:01")
        val target = SimpleTimePeriod("2000-01-01 00:02", "2000-01-01 00:03")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(false))
    }

    @Test
    fun `doOverlap - contiguous is overlap`() {
        val actor = SimpleTimePeriod("2000-01-01 00:00", "2000-01-01 00:01")
        val target = SimpleTimePeriod("2000-01-01 00:01", "2000-01-01 00:02")

        assertThat(TimePeriodHelper.doOverlap(actor, target), `is`(true))

        val actor2 = SimpleTimePeriod("2000-01-01 00:01", "2000-01-01 00:02")
        val target2 = SimpleTimePeriod("2000-01-01 00:00", "2000-01-01 00:01")

        assertThat(TimePeriodHelper.doOverlap(actor2, target2), `is`(true))
    }


    @Test
    fun `doOverlap - contiguous is not overlap`() {
        val actor = SimpleTimePeriod("2000-01-01 00:00", "2000-01-01 00:01")
        val target = SimpleTimePeriod("2000-01-01 00:01", "2000-01-01 00:02")

        assertThat(TimePeriodHelper.doOverlap(actor, target, false), `is`(false))

        val actor2 = SimpleTimePeriod("2000-01-01 00:01", "2000-01-01 00:02")
        val target2 = SimpleTimePeriod("2000-01-01 00:00", "2000-01-01 00:01")

        assertThat(TimePeriodHelper.doOverlap(actor2, target2, false), `is`(false))
    }
}