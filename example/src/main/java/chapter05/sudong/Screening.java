package chapter05.sudong;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; //2. 예매에 필요한 필드를 추가한다.
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Reservation reserve(Customer customer, int audienceCount) { //1. 예매하다. 어떤 사람이 몇개 구매할 지 알아야 한다.
        return new Reservation(customer, this, this.calculateFee(audienceCount), audienceCount); //반환된 요금에 사람 수를 곱해서 전체 예매 요금을 계산한 후 reservation을 반환한다.
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount); //영화가 영화 금액을 계산한한다. // 중요한 점은 movie의 내부 구현에 대해 아무 정보 없이 구현했다는 것이다. 완벽하게 캡슐화 해냈다.
        //이제 screening과 moive를 연결하는 연결점은 '오로지 메세지'뿐이다.
    }

    public int getSequence() {
        return sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "movie=" + movie +
                ", sequence=" + sequence +
                ", whenScreened=" + whenScreened +
                '}';
    }
}
