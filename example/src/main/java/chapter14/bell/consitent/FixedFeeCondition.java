package chapter14.bell.consitent;

import java.util.List;

public class FixedFeeCondition implements FeeCondition {

  @Override
  public List<DateTimeInterval> findTimeIntervals(final Call call) {
    return List.of(call.getInterval());
  }
}
