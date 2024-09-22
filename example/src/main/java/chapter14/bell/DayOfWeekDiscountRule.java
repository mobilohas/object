package chapter14.bell;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DayOfWeekDiscountRule {
  private List<DayOfWeek> dayOfWeeks = new ArrayList<>();
  private Duration duration = Duration.ZERO;
  private Money amount = Money.ZERO;

  public DayOfWeekDiscountRule(final List<DayOfWeek> dayOfWeeks, final Duration duration,
      final Money amount) {
    this.dayOfWeeks = dayOfWeeks;
    this.duration = duration;
    this.amount = amount;
  }

  public Money calculate(DateTimeInterval interval) {
    if (dayOfWeeks.contains(interval.getFrom().getDayOfWeek())) {
      return amount.times(interval.duration().getSeconds() / duration.getSeconds());
    }
    return Money.ZERO;
  }
}
