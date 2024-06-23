package chapter02.sudong.domain;

public interface DiscountCondition {
  boolean isSatisfiedBy(Screening screening);
}
