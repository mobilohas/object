package chapter14.bell.consitent;

import java.time.Duration;

public class FeePerDuration {
  private Money fee;
  private Duration duration;

  public FeePerDuration(final Money fee, final Duration duration) {
    this.fee = fee;
    this.duration = duration;
  }

  public Money calculate(DateTimeInterval interval) {
    return fee.times(interval.duration().getSeconds() / duration.getSeconds());
  }
}
