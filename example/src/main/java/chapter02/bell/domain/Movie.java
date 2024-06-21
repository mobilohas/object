package chapter02.bell.domain;

import java.time.Duration;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Movie(final String title, final Duration runningTime, final Money fee,
      final DiscountPolicy discountPolicy) {
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountPolicy = discountPolicy;
  }

  public Money getFee() {
    return fee;
  }

  public Money calculateMovieFee(Screening screening) {
    return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }

  public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }
}
