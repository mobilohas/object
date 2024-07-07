package chapter04.bell.datadriven;

import java.time.LocalDateTime;

public class Screening {

  private Movie movie;
  private int sequence;
  private LocalDateTime whenScreened;

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(final Movie movie) {
    this.movie = movie;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(final int sequence) {
    this.sequence = sequence;
  }

  public LocalDateTime getWhenScreened() {
    return whenScreened;
  }

  public void setWhenScreened(final LocalDateTime whenScreened) {
    this.whenScreened = whenScreened;
  }
}
