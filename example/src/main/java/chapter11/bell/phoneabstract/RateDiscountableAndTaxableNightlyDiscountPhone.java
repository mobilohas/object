package chapter11.bell.phoneabstract;

import java.time.Duration;

public class RateDiscountableAndTaxableNightlyDiscountPhone extends RateDiscountableRegularPhone {
  private double taxRate;

  public RateDiscountableAndTaxableNightlyDiscountPhone(final Money amount,
      final Duration seconds, final Money discountAmount, final double taxRate) {
    super(amount, seconds, discountAmount);
    this.taxRate = taxRate;
  }

  @Override
  protected Money afterCalculated(final Money fee) {
    return super.afterCalculated(fee).plus(fee.times(taxRate));
  }
}
