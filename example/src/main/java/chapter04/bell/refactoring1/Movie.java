package chapter04.bell.refactoring1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Money calculateFee(LocalDateTime whenScreened, int sequence) {
    return fee.minus(discountPolicy.calculateDiscountAmount(whenScreened, sequence, fee));
  }
}
