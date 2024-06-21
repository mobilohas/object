package chapter02.bell.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DefaultDiscountPolicy implements DiscountPolicy {

  private List<DiscountCondition> conditions = new ArrayList<>();

  public DefaultDiscountPolicy(DiscountCondition ... conditions) {
    this.conditions = Arrays.asList(conditions);
  }

  @Override
  public Money calculateDiscountAmount(Screening screening) {
    for (DiscountCondition condition: conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return getDiscountAmount(screening);
      }
    }
    return Money.ZERO;
  }

  abstract protected Money getDiscountAmount(final Screening screening);
}
