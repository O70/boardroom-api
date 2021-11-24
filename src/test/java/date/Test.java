package date;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/11/23 16:32
 */
public class Test {

    public static void main(String[] args) {
        /*LocalDate start = LocalDate.of(2021, 9,8);
        LocalDate end = LocalDate.of(2024, 10,10);

        //new Test().calc(start, end);
        Test t = new Test();
        //t.calcV2(start, end);
        //System.out.println("***********");
        //t.calcV2(LocalDate.of(2021, 9,8), LocalDate.of(2024, 10,10));

        Stream.of(
            //new Item(start, end)
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2024, 10,10)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2024, 10,18)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 10,18)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 10,31)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 11,2)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 11,16)),
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 12,16)),
            //
            //new Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 12,15)),
            //new Item(LocalDate.of(2021, 9,15), LocalDate.of(2021, 12,15)),
            new Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,14)),
            new Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,15)),
            new Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,16))
        ).forEach(it -> {
            *//*int y = 2024;
            List<Item> items = t.calcV3(it.getStart(), it.getEnd());
            Item item1 = items.parallelStream().filter(i -> i.getYear() == y).findFirst().orElse(null);
            System.out.println(item1);

            Stream<Item> stream = t.calcV2(it.getStart(), it.getEnd());
            Item item = stream.filter(i -> i.getYear() == y).findFirst().orElse(null);
            System.out.println(item);*//*

            t.calcV3(it.getStart(), it.getEnd()).forEach(System.out::println);
            System.out.println("*******************");
        });*/

        LocalDate start = LocalDate.of(2021, 9,8);
        LocalDate end = LocalDate.of(2024, 10,10);

        //List<MonthUtils.Item> calc = MonthUtils.calc(start, end);
        //System.out.println(calc);

        Stream.of(
                //new MonthUtils.Item(start, end)
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2024, 10,10)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2024, 10,18)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 10,18)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 10,31)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 11,2)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 11,16)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 12,16)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,17), LocalDate.of(2021, 12,15)),
                //new MonthUtils.Item(LocalDate.of(2021, 9,15), LocalDate.of(2021, 12,15)),
                //new MonthUtils.Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,14)),
                //new MonthUtils.Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,15)),
                //new MonthUtils.Item(LocalDate.of(2021, 8,18), LocalDate.of(2021, 12,16))
                //new MonthUtils.Item(LocalDate.of(2021, 12,18), LocalDate.of(2021, 12,18))
                new MonthUtils.Item(LocalDate.of(2021, 12,18), null)
        ).forEach(it -> {
            //List<MonthUtils.Item> calc = MonthUtils.calc(it);
            //calc.stream().forEach(System.out::println);

            MonthUtils.calcStream(it).forEach(System.out::println);

            //MonthUtils.
            //
            //MonthUtils.calcStream(start, end, 10)
        });
    }

    public List<Item> calcV3(LocalDate start, LocalDate end) {
        return this.calcV2(start, end).collect(Collectors.toList());
    }

    public Item find(LocalDate start, LocalDate end, int year) {
        return this.calcV2(start, end).filter(i -> i.getYear() == year).findFirst().orElse(null);
    }

    public Stream<Item> calcV2(LocalDate start, LocalDate end) {
        int base = start.getYear();
        int diff = end.getYear() - start.getYear();

        BiPredicate<LocalDate, LocalDate> validate = (s, e) -> Period.between(s, e).getDays() + 1 < 15;

        BiFunction<LocalDate, LocalDate, LocalDate> build = (b, p) ->
                b.withMonth(p.getMonthValue()).withDayOfMonth(p.getDayOfMonth());

        return IntStream.range(0, diff + 1).mapToObj(i -> {
            int year = base + i;

            // Step 1
            //LocalDate s = LocalDate.of(yearInd, first ? start.getMonthValue() : 1, first ? start.getDayOfMonth() : 1);
            //LocalDate e = LocalDate.of(yearInd, last ? end.getMonthValue() : 12, last ? end.getDayOfMonth() : 31);

            LocalDate s = LocalDate.MIN.withYear(year);
            LocalDate e = LocalDate.MAX.withYear(year);

            if (i == 0) {
                //s = s.withMonth(start.getMonthValue()).withDayOfMonth(start.getDayOfMonth());
                s = build.apply(s, start);
            }

            if (i == diff) {
                //e = e.withMonth(end.getMonthValue()).withDayOfMonth(end.getDayOfMonth());
                e = build.apply(e, end);
            }

            // Step 2
            int m = e.getMonthValue() - s.getMonthValue() + 1;

            // Step 3
            /*if (Period.between(s, s.with(TemporalAdjusters.lastDayOfMonth())).getDays() + 1 < size) {
                m -= 1;
            }

            if (Period.between(e.with(TemporalAdjusters.firstDayOfMonth()), e).getDays() + 1 < size) {
                m -= 1;
            }*/

            if (validate.test(s, s.with(TemporalAdjusters.lastDayOfMonth()))) {
                m -= 1;
            }

            if (validate.test(e.with(TemporalAdjusters.firstDayOfMonth()), e)) {
                m -= 1;
            }

            return new Item(s, e, year, m);
        });
    }

    public void calc(LocalDate start, LocalDate end) {
        int base = start.getYear();
        int diff = end.getYear() - start.getYear() + 1;

        List<Item> items = new ArrayList<>(diff);
        for (int i = 0; i < diff; i++) {
            boolean first = i == 0;
            boolean last = i == diff - 1;
            items.add(new Item(
                    LocalDate.of(base + i, first ? start.getMonthValue() : 1, first ? start.getDayOfMonth() : 1),
                    LocalDate.of(base + i, last ? end.getMonthValue() : 12, last ? end.getDayOfMonth() : 31),
                    0
            ));
        }

        items.forEach(it -> {
            System.out.println(it);
            Period between = Period.between(it.getStart(), it.getEnd());
            System.out.println(between.getMonths() + 1);
            System.out.println("*********");
        });
    }

    static class Item {

        private LocalDate start;

        private LocalDate end;

        private int year;

        private int months;

        public Item(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public Item(LocalDate start, LocalDate end, int months) {
            this.start = start;
            this.end = end;
            this.months = months;
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
