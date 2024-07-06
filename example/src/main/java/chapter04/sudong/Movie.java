package chapter04.sudong;

import java.util.List;

/**
 * 데이터 중심의 설계를 해봅시다.
 * 이를 위해서는 우선 객체에 필요한 모든 데이터를 정의합니다.
 * Movie는 이름, 상영 시간, 요금, 할인되는 조건, 할인 타입, 할인 금액을 모두 가지고 있다.
 */
public class Movie {
    private String title;
    private String runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType; // 할인 정책의 종류를 결정하는 ㅇ려거형.
    private Money discountAmount;
    private double discountPercent;

    // 캡슐화를 위해서 데이터에 대한 접근자와 수정자를 추가합니다.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
