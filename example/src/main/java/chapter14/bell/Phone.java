package chapter14.bell;

import java.util.ArrayList;
import java.util.List;

public class Phone {
  private RatePolicy ratePolicy;
  private List<Call> calls = new ArrayList<>();

  public Phone(final RatePolicy ratePolicy) {
    this.ratePolicy = ratePolicy;
  }

  public List<Call> getCalls() {
    return calls;
  }

  public void call(Call call) {
    calls.add(call);
  }

  public Money calculateFee() {
    return ratePolicy.calculateFee(this);
  }
}
