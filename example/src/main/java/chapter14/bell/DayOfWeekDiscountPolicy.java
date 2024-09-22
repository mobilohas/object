package chapter14.bell;

import java.util.ArrayList;
import java.util.List;

public class DayOfWeekDiscountPolicy extends BasicRatePolicy {
  private List<DayOfWeekDiscountRule> rules = new ArrayList<>();

  public DayOfWeekDiscountPolicy(final List<DayOfWeekDiscountRule> rules) {
    this.rules = rules;
  }

  @Override
  protected Money calculateCallFee(final Call call) {
    Money result = Money.ZERO;
    for (DateTimeInterval interval: call.getInterval().splitByDay()) {
      for (DayOfWeekDiscountRule rule: rules) {
        result.plus(rule.calculate(interval));
      }
    }
    return result;
  }
}
