package chapter11.bell.phonecomposition;

public abstract class TaxablePolicy extends AdditionalRatePolicy {
  private double taxRatio;

  public TaxablePolicy(final RatePolicy next, final double taxRatio) {
    super(next);
    this.taxRatio = taxRatio;
  }

  @Override
  protected Money afterCalculated(final Money fee) {
    return fee.plus(fee.times(taxRatio));
  }
}
