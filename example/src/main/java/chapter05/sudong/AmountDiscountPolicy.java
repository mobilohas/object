package chapter05.sudong;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
    protected Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition2... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
