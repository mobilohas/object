package chapter02.bell.domain;

public interface DiscountCondition {
  boolean isSatisfiedBy(Screening screening);
}
