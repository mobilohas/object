package chapter04.bell.refactoring2;

import java.time.Duration;
import java.time.LocalDateTime;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Money calculateFee(LocalDateTime whenScreened, int sequence) {
    return fee.minus(discountPolicy.calculateDiscountAmount(whenScreened, sequence, fee));
  }
}
