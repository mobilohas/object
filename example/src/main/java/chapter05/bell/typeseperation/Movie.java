package chapter05.bell.typeseperation;

import java.time.Duration;
import java.util.List;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private List<PeriodCondition> periodConditions;
  private List<SequenceCondition> sequenceConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public Money calculateMovieFee(final Screening screening) {
    if (isDiscountable(screening)) {
      return fee.minus(calculateDiscountAmount());
    }
    return fee;
  }

  private boolean isDiscountable(final Screening screening) {
    return checkPeriodConditions(screening) || checkSequenceConditions(screening);
  }

  private boolean checkPeriodConditions(Screening screening) {
    return periodConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
  }

  private boolean checkSequenceConditions(Screening screening) {
    return sequenceConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
  }

  private Money calculateDiscountAmount() {
    return switch (movieType) {
      case AMOUNT_DISCOUNT -> calculateAmountDiscountAmount();
      case PERCENT_DISCOUNT -> calculatePercentDiscountAmount();
      case NONE_DISCOUNT -> calculateNoneDiscountAmount();
      default -> throw new IllegalStateException();
    };
  }

  private Money calculateAmountDiscountAmount() {
    return discountAmount;
  }

  private Money calculatePercentDiscountAmount() {
    return fee.times(discountPercent);
  }

  private Money calculateNoneDiscountAmount() {
    return Money.ZERO;
  }
}
