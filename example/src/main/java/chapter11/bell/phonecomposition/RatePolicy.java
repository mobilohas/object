package chapter11.bell.phonecomposition;

public interface RatePolicy {
  Money calculateFee(Phone phone);
}
