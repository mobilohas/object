package chapter14.bell.consitent;

import java.util.List;

public interface FeeCondition {
  List<DateTimeInterval> findTimeIntervals(Call call);
}
