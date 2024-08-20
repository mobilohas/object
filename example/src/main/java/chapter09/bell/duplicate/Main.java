package chapter09.bell.duplicate;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

  public static void main(String[] args) {
    Phone phone = new Phone(Money.wons(5), Duration.ofSeconds(10));
    phone.call(new Call(
        LocalDateTime.of(2024, 3, 13, 12, 10, 0),
        LocalDateTime.of(2024, 3, 13, 12, 11, 0)));
    phone.call(new Call(
        LocalDateTime.of(2024, 3, 14, 12, 10, 0),
        LocalDateTime.of(2024, 3, 14, 12, 11, 0)));
    Money result = phone.calculateFee();
    System.out.println(result);
  }
}
