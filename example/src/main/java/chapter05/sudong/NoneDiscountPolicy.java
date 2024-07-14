package chapter05.sudong;


import java.time.Duration;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return null;
    }
}
