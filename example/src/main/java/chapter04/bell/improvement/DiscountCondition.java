package chapter04.bell.improvement;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

  private DiscountConditionType type;
  private int sequence;

  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;


  public DiscountConditionType getType() {
    return type;
  }

  public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
    if (!type.equals(DiscountConditionType.PERIOD)) {
      throw new IllegalArgumentException();
    }

    return this.dayOfWeek.equals(dayOfWeek)
        && this.startTime.compareTo(time) <= 0
        && this.endTime.compareTo(time) >= 0;
  }

  public boolean isDiscountable(int sequence) {
    if (!type.equals(DiscountConditionType.SEQUENCE)) {
      throw new IllegalArgumentException();
    }
    return this.sequence == sequence;
  }
}
