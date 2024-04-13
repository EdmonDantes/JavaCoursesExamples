package task14;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Task14 {

    public static void main(String[] args) {
        System.out.println(DTC.toIndustrial(178));
    }

    // 4
    private static boolean task4Kata(LocalDate last, LocalDate today, int cycleLength) {
        return today.toEpochDay() - last.toEpochDay() > cycleLength;
    }

    // 5
    private static int task5Kata(int year) {
        LocalDate date = LocalDate.of(year, 1, 13);
        int count = 0;
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                count++;
            }
            date = date.plusMonths(1);
        }
        return count;
    }

    // 6
    private static String task6Kata(Date d) {
        LocalTime time = LocalTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
        long minutes = (24 * 60 * 60 - Duration.between(LocalTime.MIDNIGHT, time).get(ChronoUnit.SECONDS)) / 60;
        return minutes + " " + (minutes < 2 ? "minute" : "minutes");
    }

    // 7
    private static String task7Kata(int year, int month, int day) {
        long days = LocalDate.now().toEpochDay() - LocalDate.of(year, month, day).toEpochDay();
        return "You are " + days + " days old";
    }

    // 8
    public static class DTC {
        public static double toIndustrial(String time) {
            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);
            return toIndustrial(hour * 60 + minutes);
        }

        public static double toIndustrial(int time) {
            double result = (time / 60 + time % 60 / 60.0) * 100;
            result = result % 1.0 > 0.5 ? Math.ceil(result) : Math.floor(result);
            return result / 100;
        }

        public static String toNormal(double time) {
            int hour = (int) time;
            double minutes = ((time % 1) * 60);
            minutes = minutes % 1.0 > 0.5 ? Math.ceil(minutes) : Math.floor(minutes);

            int normalMinutes = (int) minutes;

            return String.format("%d:%02d", hour + normalMinutes / 60, normalMinutes % 60);
        }
    }
}
