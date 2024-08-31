package chapter11.bell.phoneabstract;

import java.time.Duration;

public class TaxableAndRateDiscountableRegularPhone extends TaxableRegularPhone {
  private Money discountAmount;

  public TaxableAndRateDiscountableRegularPhone(final Money amount, final Duration seconds,
      final double taxRate, final Money discountAmount) {
    super(amount, seconds, taxRate);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money afterCalculated(final Money fee) {
    return super.afterCalculated(fee).minus(discountAmount);
  }
}
