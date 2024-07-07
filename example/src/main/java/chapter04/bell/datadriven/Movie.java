package chapter04.bell.datadriven;

import java.time.Duration;
import java.util.List;

public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private List<DiscountCondition> discountConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public Duration getRunningTime() {
    return runningTime;
  }

  public void setRunningTime(final Duration runningTime) {
    this.runningTime = runningTime;
  }

  public Money getFee() {
    return fee;
  }

  public void setFee(final Money fee) {
    this.fee = fee;
  }

  public List<DiscountCondition> getDiscountConditions() {
    return discountConditions;
  }

  public void setDiscountConditions(
      final List<DiscountCondition> discountConditions) {
    this.discountConditions = discountConditions;
  }

  public MovieType getMovieType() {
    return movieType;
  }

  public void setMovieType(final MovieType movieType) {
    this.movieType = movieType;
  }

  public Money getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(final Money discountAmount) {
    this.discountAmount = discountAmount;
  }

  public double getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(final double discountPercent) {
    this.discountPercent = discountPercent;
  }
}
