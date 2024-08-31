package chapter11.bell.phoneabstract;

import java.time.Duration;

public class RateDiscountableNightlyDiscountPhone extends NightlyDiscountPhone {
  private Money discountAmount;

  public RateDiscountableNightlyDiscountPhone(final Money nightlyAmount, final Money regularAmount,
      final Duration seconds, final Money discountAmount) {
    super(nightlyAmount, regularAmount, seconds);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money afterCalculated(final Money fee) {
    return fee.minus(discountAmount);
  }
}
