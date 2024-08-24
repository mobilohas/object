package chapter09.bell.refactoring;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {

  private List<Call> calls = new ArrayList<>();

  public List<Call> getCalls() {
    return calls;
  }

  public void call(Call call) {
    calls.add(call);
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call: calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result;
  }

  abstract protected Money calculateCallFee(Call call);
}
