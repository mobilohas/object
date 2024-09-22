package chapter14.bell;

public interface RatePolicy {
  Money calculateFee(Phone phone);
}
