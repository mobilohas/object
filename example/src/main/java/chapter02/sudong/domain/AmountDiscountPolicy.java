package chapter02.sudong.domain;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

  protected Money discountAmount;

  public AmountDiscountPolicy(final Money discountAmount, final DiscountCondition... conditions) {
    super(conditions);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money getDiscountAmount(final Screening screening) {
    return discountAmount;
  }
}
