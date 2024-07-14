package chapter05.bell.polymorphism2;

import java.time.Duration;
import java.util.List;

public class NoneDiscountMovie extends Movie{

  public NoneDiscountMovie(final String title, final Duration runningTime, final Money fee,
      final List<DiscountCondition> discountConditions) {
    super(title, runningTime, fee, discountConditions);
  }

  @Override
  protected Money calculateDiscountAmount() {
    return Money.ZERO;
  }
}
