package chapter06.bell.improvement;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Action {

  public static void action1() {
    Event meeting = new Event(
        "회의", LocalDateTime.of(2019,5,8,10,30), Duration.ofMinutes(30));
    RecurringSchedule schedule = new RecurringSchedule(
        "회의", DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), Duration.ofMinutes(30));

    if (!meeting.isSatisfied(schedule)) {
      meeting.reschedule(schedule);
    }
  }
}
