package chapter09.bell.modifying;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NightlyDiscountPhone {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private Money regularAmount;
  private Duration seconds;
  private List<Call> calls = new ArrayList<>();
  private double taxRate;

  public NightlyDiscountPhone(final Money nightlyAmount, final Money regularAmount,
      final Duration seconds, final double taxRate) {
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
    this.taxRate = taxRate;
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call: calls) {
      if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
        result = result.plus(nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
      } else {
        result = result.plus(
            regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
      }
    }
    return result.minus(result.times(taxRate)); // 이런식으로 버그가 만들어질 수도 있다.
  }
}
