package chapter04.sudong;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 데이터 주도 설계로 할인 조건을 설계해 봅시다.
 * 할인 조건 타입이 있어야 하고,
 * 할인 조건이 순번일 때를 위해서 sequence를
 * 할인 조건이 기간일 때를 위해서 dayOfWeek, startTime, endTime을 가지고 있어야 합니다.
 */
public class DiscountCondition {
  private DiscountConditionType type;

  private int sequence;

  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public DiscountConditionType getType() {
    return type;
  }

  public void setType(DiscountConditionType type) {
    this.type = type;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }
}
