package chapter05.sudong;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private double discountPercent;

    public PercentDiscountPolicy(double discountPercent, DiscountCondition2... conditions) {
        super(conditions);
        this.discountPercent = discountPercent;
    }

    @Override
    protected Money getDiscountAmount(final Screening screening) {
        return screening.getMovieFee().times(discountPercent);
    }
}
