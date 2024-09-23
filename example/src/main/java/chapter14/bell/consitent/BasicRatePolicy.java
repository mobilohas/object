package chapter14.bell.consitent;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicRatePolicy implements RatePolicy {
  private List<FeeRule> feeRules = new ArrayList<>();

  @Override
  public Money calculateFee(final Phone phone) {
    return phone.getCalls()
        .stream()
        .map(this::calculate)
        .reduce(Money.ZERO, Money::plus);
  }

  private Money calculate(final Call call) {
    return feeRules
        .stream()
        .map(rule -> rule.calculateFee(call))
        .reduce(Money.ZERO, Money::plus);
  }
}
