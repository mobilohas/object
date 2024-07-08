package chapter04.bell.refactoring1;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountPolicy {

  private DiscountType discountType;
  private Money discountAmount;
  private double discountPercent;
  private List<DiscountCondition> discountConditions;

  public DiscountPolicy(final DiscountType discountType, final Money discountAmount,
      final double discountPercent,
      final List<DiscountCondition> discountConditions) {
    this.discountType = discountType;
    this.discountAmount = discountAmount;
    this.discountPercent = discountPercent;
    this.discountConditions = discountConditions;
  }

  public Money calculateDiscountAmount(LocalDateTime whenScreened, int sequence, Money movieFee) {
    if (!isDiscountable(whenScreened, sequence)) {
      return Money.ZERO;
    }
    return switch (discountType) {
      case AMOUNT_DISCOUNT -> discountAmount;
      case PERCENT_DISCOUNT -> movieFee.times(discountPercent);
      case NONE_DISCOUNT -> Money.ZERO;
    };
  }

  public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
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
