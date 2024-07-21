package chapter06.sudong;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventMain01 {
    public static void main(String[] args) {
        Event event = new Event("회의",
                LocalDateTime.of(2024, 7, 21, 10, 30),
                Duration.ofMinutes(30));

        RecurringSchedule schedule = new RecurringSchedule("회의",
                DayOfWeek.WEDNESDAY,
                LocalTime.of(10, 30),
                Duration.ofMinutes(30));
    }
}
