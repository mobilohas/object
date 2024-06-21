package chapter02.bell.domain;

public interface DiscountPolicy {

  Money calculateDiscountAmount(Screening screening);
}
