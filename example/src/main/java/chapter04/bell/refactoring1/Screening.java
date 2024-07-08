package chapter04.bell.refactoring1;

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
    return movie.calculateFee(whenScreened, sequence).times(audienceCount);
  }
}
