package chapter04.bell.refactoring2;

import java.time.LocalDateTime;
import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy{

  private double discountPercent;

  public PercentDiscountPolicy(final double discountPercent, final List<DiscountCondition> discountConditions) {
    super(discountConditions);
    this.discountPercent = discountPercent;
  }

  @Override
  public Money calculateDiscountAmount(
      final LocalDateTime whenScreened, final int sequence, final Money movieFee) {
    if (isDiscountable(whenScreened, sequence)) {
      return movieFee.times(discountPercent);
    }
    return Money.ZERO;
  }
}
