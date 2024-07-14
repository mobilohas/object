package chapter05.sudong;

public class SequenceDiscountCondition implements DiscountCondition2{
    private int sequence;

    public SequenceDiscountCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return this.sequence == screening.getSequence();
    }
}
