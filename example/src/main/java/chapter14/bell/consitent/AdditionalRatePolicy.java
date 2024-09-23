package chapter14.bell.consitent;

public abstract class AdditionalRatePolicy implements RatePolicy {
  private RatePolicy next;

  public AdditionalRatePolicy(final RatePolicy next) {
    this.next = next;
  }

  @Override
  public Money calculateFee(final Phone phone) {
    Money fee = next.calculateFee(phone);
    return afterCalculated(fee);
  }

  abstract protected Money afterCalculated(Money fee);
}
