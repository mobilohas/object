package chapter11.bell.phonecomposition;

import java.time.Duration;

public class RegularPolicy extends BasicRatePolicy {
  private Money amount;
  private Duration seconds;

  public RegularPolicy(final Money amount, final Duration seconds) {
    this.amount = amount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(final Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
