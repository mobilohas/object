package chapter14.bell.consitent;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DurationFeeCondition implements FeeCondition {
  private Duration from;
  private Duration to;

  public DurationFeeCondition(final Duration from, final Duration to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public List<DateTimeInterval> findTimeIntervals(final Call call) {
    if (call.getInterval().duration().compareTo(from) < 0) {
      return Collections.emptyList();
    }
    return List.of(DateTimeInterval.of(
        call.getInterval().getFrom().plus(from),
        call.getInterval().duration().compareTo(to) > 0 ?
            call.getInterval().getFrom().plus(to) :
            call.getInterval().getTo()));
  }
}
