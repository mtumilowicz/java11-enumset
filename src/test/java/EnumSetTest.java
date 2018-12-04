import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumSet;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2018-12-04.
 */
public class EnumSetTest {

    @Test
    public void allOf() {
        EnumSet<DayOfWeek> dayOfWeeks = EnumSet.allOf(DayOfWeek.class);

        assertThat(dayOfWeeks.size(), is(7));
    }

    @Test
    public void complementOf() {
        EnumSet<DayOfWeek> withoutWeekendDays = EnumSet.complementOf(
                EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
        );

        assertThat(withoutWeekendDays.size(), is(5));
        assertThat(withoutWeekendDays, contains(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY)
        );
    }

    @Test
    public void range() {
        EnumSet<DayOfWeek> workingDays = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

        assertThat(workingDays.size(), is(5));
        assertThat(workingDays, contains(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY)
        );
    }

    @Test
    public void noneOf() {
        EnumSet<DayOfWeek> emptyDays = EnumSet.noneOf(DayOfWeek.class);

        assertTrue(emptyDays.isEmpty());
    }
}
