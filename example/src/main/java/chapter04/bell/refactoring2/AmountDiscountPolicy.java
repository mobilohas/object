package chapter04.bell.refactoring2;

import java.time.LocalDateTime;
import java.util.List;

public class AmountDiscountPolicy extends DiscountPolicy {

  private Money discountAmount;

  public AmountDiscountPolicy(final Money discountAmount, final List<DiscountCondition> discountConditions) {
    super(discountConditions);
    this.discountAmount = discountAmount;
  }

  @Override
  public Money calculateDiscountAmount(
      final LocalDateTime whenScreened, final int sequence, final Money movieFee) {
    if (isDiscountable(whenScreened, sequence)) {
      return discountAmount;
    }
    return Money.ZERO;
  }
}
