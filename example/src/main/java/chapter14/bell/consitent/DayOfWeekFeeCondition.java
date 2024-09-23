package chapter14.bell.consitent;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayOfWeekFeeCondition implements FeeCondition {
  private List<DayOfWeek> dayOfWeeks = new ArrayList<>();

  public DayOfWeekFeeCondition(final DayOfWeek ... dayOfWeeks) {
    this.dayOfWeeks = Arrays.asList(dayOfWeeks);
  }

  @Override
  public List<DateTimeInterval> findTimeIntervals(final Call call) {
    return call.getInterval()
        .splitByDay()
        .stream()
        .filter(each -> dayOfWeeks.contains(each.getFrom().getDayOfWeek()))
        .toList();
  }
}
