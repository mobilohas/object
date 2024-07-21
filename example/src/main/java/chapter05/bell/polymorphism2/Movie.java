package chapter05.bell.polymorphism2;

import java.time.Duration;
import java.util.List;

public abstract class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private List<DiscountCondition> discountConditions;

  public Movie(final String title, final Duration runningTime, final Money fee,
      final List<DiscountCondition> discountConditions) {
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountConditions = discountConditions;
  }

  public Money calculateMovieFee(final Screening screening) {
    if (isDiscountable(screening)) {
      return fee.minus(calculateDiscountAmount());
    }
    return fee;
  }

  private boolean isDiscountable(final Screening screening) {
    return discountConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
  }

  abstract protected Money calculateDiscountAmount();

  protected Money getFee() {
    return fee;
  }
}
