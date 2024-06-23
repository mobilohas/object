package chapter02.sudong.domain;

public interface DiscountPolicy {

  Money calculateDiscountAmount(Screening screening);
}
