package date;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/11/24 09:01
 */
public abstract class MonthUtils {

    private static final int ROUND = 15;

    public static List<Item> calc(Item item) {
        return calc(item, ROUND);
    }

    public static List<Item> calc(Item item, int round) {
        return calc(item.getStart(), item.getEnd(), round);
    }

    public static Stream<Item> calcStream(Item item) {
        return calcStream(item, ROUND);
    }

    public static Stream<Item> calcStream(Item item, int round) {
        return calcStream(item.getStart(), item.getEnd(), round);
    }

    public static List<Item> calc(LocalDate start, LocalDate end) {
        return calc(start, end, ROUND);
    }

    public static List<Item> calc(LocalDate start, LocalDate end, int round) {
        return calcStream(start, end, round).collect(Collectors.toList());
    }

    public static Item find(LocalDate start, LocalDate end, int year) {
        return find(start, end, year, ROUND);
    }

    public static Item find(LocalDate start, LocalDate end, int year, int round) {
        return calcStream(start, end, round).filter(it -> it.getYear() == year).findFirst().orElse(null);
    }

    public static Stream<Item> calcStream(LocalDate start, LocalDate end) {
        return calcStream(start, end, ROUND);
    }

    public static Stream<Item> calcStream(LocalDate start, LocalDate end, int round) {
        Objects.requireNonNull(start, "start is null");
        Objects.requireNonNull(end, "end is null");

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("date Illegal");
        }

        final int startYear = start.getYear(), endYear = end.getYear();

        BiPredicate<LocalDate, LocalDate> validate = (s, e) -> Period.between(s, e).getDays() + 1 < round;

        return IntStream.range(startYear, endYear + 1).mapToObj(year -> {
            LocalDate s = startYear == year ? LocalDate.of(year, start.getMonth(), start.getDayOfMonth()) : LocalDate.MIN.withYear(year);
            LocalDate e = endYear == year ? LocalDate.of(year, end.getMonth(), end.getDayOfMonth()) : LocalDate.MAX.withYear(year);

            int m;

            if (s.getMonthValue() == e.getMonthValue()) {
                m = e.getDayOfMonth() - s.getDayOfMonth() + 1 < round ? 0 : 1;
            } else {
                m = e.getMonthValue() - s.getMonthValue() + 1;

                if (validate.test(s, s.with(TemporalAdjusters.lastDayOfMonth()))) {
                    m -= 1;
                }

                if (validate.test(e.with(TemporalAdjusters.firstDayOfMonth()), e)) {
                    m -= 1;
                }
            }

            return new Item(s, e, year, m);
        });
    }

    public static class Item {

        private LocalDate start;

        private LocalDate end;

        private int year;

        private int months;

        public Item(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public Item(LocalDate start, LocalDate end, int year, int months) {
            this.start = start;
            this.end = end;
            this.year = year;
            this.months = months;
        }

        public LocalDate getStart() {
            return start;
        }

        public Item setStart(LocalDate start) {
            this.start = start;
            return this;
        }

        public LocalDate getEnd() {
            return end;
        }

        public Item setEnd(LocalDate end) {
            this.end = end;
            return this;
        }

        public int getYear() {
            return year;
        }

        public Item setYear(int year) {
            this.year = year;
            return this;
        }

        public int getMonths() {
            return months;
        }

        public Item setMonths(int months) {
            this.months = months;
            return this;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "start=" + start +
                    ", end=" + end +
                    ", year=" + year +
                    ", months=" + months +
                    '}';
        }

    }

}
