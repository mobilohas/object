package chapter09.bell.refactoring.add;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {

  private double taxRate;
  private List<Call> calls = new ArrayList<>();

  public Phone(final double taxRate) {
    this.taxRate = taxRate;
  }

  public void call(Call call) {
    calls.add(call);
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call: calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result.plus(result.times(taxRate));
  }

  abstract protected Money calculateCallFee(Call call);
}
