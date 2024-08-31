package chapter11.bell.phoneabstract;

import java.time.Duration;

public class TaxableAndRateDiscountableNightlyDiscountPhone extends TaxableNightlyDiscountPhone {
  private Money discountAmount;

  public TaxableAndRateDiscountableNightlyDiscountPhone(final Money nightlyAmount,
      final Money regularAmount, final Duration seconds, final double taxRate,
      final Money discountAmount) {
    super(nightlyAmount, regularAmount, seconds, taxRate);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money afterCalculated(final Money fee) {
    return super.afterCalculated(fee).minus(fee);
  }
}
