package chapter14.bell;

import java.time.Duration;

public class FixedFeePolicy extends BasicRatePolicy {
  private Money amount;
  private Duration seconds;

  public FixedFeePolicy(final Money amount, final Duration seconds) {
    this.amount = amount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(final Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
