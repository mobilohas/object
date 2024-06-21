package chapter02.bell;

import chapter02.bell.domain.Money;
import chapter02.bell.domain.Movie;
import chapter02.bell.domain.NoneDiscountPolicy;
import java.time.Duration;

public class MovieMain {

  public static void main(String[] args) {

    Movie starWars = new Movie(
        "스타워즈",
        Duration.ofMillis(210),
        Money.wons(10000),
        new NoneDiscountPolicy());
  }
}
