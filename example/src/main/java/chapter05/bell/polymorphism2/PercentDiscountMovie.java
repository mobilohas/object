package chapter05.bell.polymorphism2;

import java.time.Duration;
import java.util.List;

public class PercentDiscountMovie extends Movie {

  private double discountPercent;

  public PercentDiscountMovie(final String title, final Duration runningTime,
      final Money fee, final List<DiscountCondition> discountConditions,
      final double discountPercent) {
    super(title, runningTime, fee, discountConditions);
    this.discountPercent = discountPercent;
  }

  @Override
  protected Money calculateDiscountAmount() {
    return getFee().times(discountPercent);
  }
}
