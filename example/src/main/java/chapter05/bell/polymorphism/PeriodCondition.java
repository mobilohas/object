package chapter05.bell.polymorphism;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {

  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public PeriodCondition(final DayOfWeek dayOfWeek, final LocalTime startTime,
      final LocalTime endTime) {
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean isSatisfiedBy(final Screening screening) {
    return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek())
        && startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0
        && endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
  }
}
