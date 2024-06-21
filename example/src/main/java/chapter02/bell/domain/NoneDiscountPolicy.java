package chapter02.bell.domain;

public class NoneDiscountPolicy implements DiscountPolicy {

  @Override
  public Money calculateDiscountAmount(final Screening screening) {
    return Money.ZERO;
  }
}
