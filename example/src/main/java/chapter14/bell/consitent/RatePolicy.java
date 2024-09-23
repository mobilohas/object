package chapter14.bell.consitent;

public interface RatePolicy {
  Money calculateFee(Phone phone);
}
