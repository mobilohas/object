package chapter05.sudong;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Movie movie = new Movie("바람과 함께 사라지다",
                Duration.ofMillis(210),
                Money.wons(10000),
                new AmountDiscountPolicy(new Money(new BigDecimal(1000)), new SequenceDiscountCondition(1)));
        Screening screening = new Screening(movie, 1, LocalDateTime.of(2024, 8, 1, 9, 0));
        movie.changeDiscountPolicy(new PercentDiscountPolicy(0.5, new SequenceDiscountCondition(1)));
        Reservation reserve = screening.reserve(customer, 2);
        System.out.println("reserve = " + reserve);

    }
}
