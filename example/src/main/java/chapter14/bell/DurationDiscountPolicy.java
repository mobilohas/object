package chapter14.bell;

import java.util.ArrayList;
import java.util.List;

public class DurationDiscountPolicy extends BasicRatePolicy {
  private List<DurationDiscountRule> rules = new ArrayList<>();

  public DurationDiscountPolicy(final List<DurationDiscountRule> rules) {
    this.rules = rules;
  }

  @Override
  protected Money calculateCallFee(final Call call) {
    Money result = Money.ZERO;
    for (DurationDiscountRule rule: rules) {
      result.plus(rule.calculate(call));
    }
    return result;
  }
}
