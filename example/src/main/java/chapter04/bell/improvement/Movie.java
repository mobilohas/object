package chapter04.bell.improvement;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private List<DiscountCondition> discountConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public MovieType getMovieType() {
    return movieType;
  }

  public Money calculateAmountDiscountFee() {
    if (!movieType.equals(MovieType.AMOUNT_DISCOUNT)) {
      throw new IllegalArgumentException();
    }
    return fee.minus(discountAmount);
  }

  public Money calculatePercentDiscountFee() {
    if (!movieType.equals(MovieType.PERCENT_DISCOUNT)) {
      throw new IllegalArgumentException();
    }
    return fee.minus(fee.times(discountPercent));
  }

  public Money calculateNoneDiscountFee() {
    if (!movieType.equals(MovieType.NONE_DISCOUNT)) {
      throw new IllegalArgumentException();
    }
    return fee;
  }

  public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
    for (DiscountCondition condition : discountConditions) {
      if (condition.getType().equals(DiscountConditionType.PERIOD)) {
        if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
          return true;
        }
      } else {
        if (condition.isDiscountable(sequence)) {
          return true;
        }
      }
    }
    return false;
  }
}
