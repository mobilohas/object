package chapter14.bell.consitent;

public class FeeRule {
  private FeeCondition feeCondition;
  private FeePerDuration feePerDuration;

  public FeeRule(final FeeCondition feeCondition, final FeePerDuration feePerDuration) {
    this.feeCondition = feeCondition;
    this.feePerDuration = feePerDuration;
  }

  public Money calculateFee(Call call) {
    return feeCondition.findTimeIntervals(call)
        .stream()
        .map(each -> feePerDuration.calculate(each))
        .reduce(Money.ZERO, Money::plus);
  }
}
