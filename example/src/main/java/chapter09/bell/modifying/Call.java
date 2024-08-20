package chapter09.bell.modifying;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {
  private LocalDateTime from;
  private LocalDateTime to;

  public Call(final LocalDateTime from, final LocalDateTime to) {
    this.from = from;
    this.to = to;
  }

  public Duration getDuration() {
    return Duration.between(from, to);
  }

  public LocalDateTime getFrom() {
    return from;
  }
}
