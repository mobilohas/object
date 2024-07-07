package chapter04.bell.improvement;

import java.time.LocalDateTime;

public class Screening {

  private Movie movie;
  private int sequence;
  private LocalDateTime whenScreened;

  public Screening(final Movie movie, final int sequence, final LocalDateTime whenScreened) {
    this.movie = movie;
    this.sequence = sequence;
    this.whenScreened = whenScreened;
  }

  public Money calculateFee(int audienceCount) {
    switch (movie.getMovieType()) {
      case AMOUNT_DISCOUNT:
        if (movie.isDiscountable(whenScreened, sequence)) {
          return movie.calculateAmountDiscountFee().times(audienceCount);
        }
        break;
      case PERCENT_DISCOUNT:
        if (movie.isDiscountable(whenScreened, sequence)) {
          return movie.calculatePercentDiscountFee().times(audienceCount);
        }
        break;
      case NONE_DISCOUNT:
        return movie.calculateNoneDiscountFee().times(audienceCount);
    }

    return movie.calculateNoneDiscountFee().times(audienceCount);
  }
}
