package chapter05.bell.start;

import chapter04.bell.datadriven.DiscountConditionType;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

  private DiscountConditionType type;
  private int sequence;
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;


  public boolean isSatisfiedBy(final Screening screening) {
    if (type == DiscountConditionType.PERIOD) {
      return isSatisfiedByPeriod(screening);
    }
    return isSatisfiedBySequence(screening);
  }

  private boolean isSatisfiedByPeriod(final Screening screening) {
    return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek())
        && startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0
        && endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
  }

  private boolean isSatisfiedBySequence(final Screening screening) {
    return sequence == screening.getSequence();
  }
}
