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
    public void allOf_notImmutable() {
        EnumSet<DayOfWeek> dayOfWeeks = EnumSet.allOf(DayOfWeek.class);

        dayOfWeeks.clear();

        assertTrue(dayOfWeeks.isEmpty());
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
    public void complementOf_notImmutable() {
        EnumSet<DayOfWeek> withoutWeekendDays = EnumSet.complementOf(
                EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
        );

        withoutWeekendDays.clear();
        
        assertTrue(withoutWeekendDays.isEmpty());
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
    public void range_notImmutable() {
        EnumSet<DayOfWeek> workingDays = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

        workingDays.clear();

        assertTrue(workingDays.isEmpty());
    }

    @Test
    public void noneOf() {
        EnumSet<DayOfWeek> emptyDays = EnumSet.noneOf(DayOfWeek.class);

        assertTrue(emptyDays.isEmpty());

        emptyDays.add(DayOfWeek.SATURDAY);
        
        assertThat(emptyDays, contains(DayOfWeek.SATURDAY));
    }
}
