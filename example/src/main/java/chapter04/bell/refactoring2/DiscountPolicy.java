package chapter04.bell.refactoring2;

import java.time.LocalDateTime;
import java.util.List;

public abstract class DiscountPolicy {

  private List<DiscountCondition> discountConditions;

  public DiscountPolicy(final List<DiscountCondition> discountConditions) {
    this.discountConditions = discountConditions;
  }

  public Money calculateDiscountAmount(LocalDateTime whenScreened, int sequence, Money movieFee) {
    if(isDiscountable(whenScreened, sequence)) {
      return calculateDiscountPolicyAmount(whenScreened, sequence, movieFee);
    }
    return Money.ZERO;
  }

  protected boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
    for (DiscountCondition condition : discountConditions) {
      if (condition.getType().equals(DiscountConditionType.PERIOD)) {
        if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
          return true;
        }
      } else {
        if (condition.isDiscountable(sequence)) {
          return true;
        }
      }
    }
    return false;
  }

  abstract protected Money calculateDiscountPolicyAmount(LocalDateTime whenScreened, int sequence, Money movieFee);
}
