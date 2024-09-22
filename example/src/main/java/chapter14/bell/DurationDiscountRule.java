package chapter14.bell;

import java.time.Duration;

public class DurationDiscountRule extends FixedFeePolicy {
  private Duration from;
  private Duration to;

  public DurationDiscountRule(final Money amount, final Duration seconds, final Duration from,
      final Duration to) {
    super(amount, seconds);
    this.from = from;
    this.to = to;
  }

  public Money calculate(Call call) {
    if (call.getDuration().compareTo(to) > 0) {
      return Money.ZERO;
    }

    if (call.getDuration().compareTo(from) < 0) {
      return Money.ZERO;
    }

    Phone phone = new Phone(null);
    phone.call(new Call(
        DateTimeInterval.of(
            call.getFrom().plus(from),
            call.getDuration().compareTo(to) > 0 ? call.getFrom().plus(to): call.getTo())
        ));
    return super.calculateFee(phone);
  }
}
