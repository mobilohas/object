package chapter11.bell.phoneinheritance;

import java.time.Duration;

public class TaxableRegularPhone extends RegularPhone {

  private double taxRate;

  public TaxableRegularPhone(final Money amount, final Duration seconds, final double taxRate) {
    super(amount, seconds);
    this.taxRate = taxRate;
  }

  @Override
  public Money calculateFee() {
    Money fee = super.calculateFee();
    return fee.plus(fee.times(taxRate));
  }
}
