package chapter05.sudong;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 변경에 취약한 코드
 * 1) 할인 조건 추가 시 변경됨
 * 2) 각 조건의 세부사항 변경시 변경됨
 */


public class DiscountCondition {
    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isSatisfiedBy(Screening screening) {
        if (type == DiscountConditionType.PERIOD) {
            return isSatisfiedByPeriod(screening);
        }
        return isSatisfiedBySequence(screening);
    }

    private boolean isSatisfiedByPeriod(Screening screening) {
        return this.dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
                this.startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                this.endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }

    private boolean isSatisfiedBySequence(Screening screening) {
        return this.sequence == screening.getSequence();
    }

}
