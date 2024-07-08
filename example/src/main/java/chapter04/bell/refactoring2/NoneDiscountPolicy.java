package chapter04.bell.refactoring2;

import java.time.LocalDateTime;
import java.util.List;

public class NoneDiscountPolicy extends DiscountPolicy {

  public NoneDiscountPolicy(final List<DiscountCondition> discountConditions) {
    super(discountConditions);
  }

  @Override
  public Money calculateDiscountPolicyAmount(
      final LocalDateTime whenScreened, final int sequence, final Money movieFee) {
    return Money.ZERO;
  }
}
