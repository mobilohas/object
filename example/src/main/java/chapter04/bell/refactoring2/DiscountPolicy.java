package chapter04.bell.refactoring2;

import java.time.LocalDateTime;
import java.util.List;

public abstract class DiscountPolicy {

  private List<DiscountCondition> discountConditions;

  public DiscountPolicy(final List<DiscountCondition> discountConditions) {
    this.discountConditions = discountConditions;
  }

  abstract public Money calculateDiscountAmount(LocalDateTime whenScreened, int sequence, Money movieFee);

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
}
