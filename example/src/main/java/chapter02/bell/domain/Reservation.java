package chapter02.bell.domain;

public class Reservation {

  private Customer customer;
  private Screening screening;
  private Money fee;
  private int audienceCount;

  public Reservation(final Customer customer, final Screening screening, final Money fee,
      final int audienceCount) {
    this.customer = customer;
    this.screening = screening;
    this.fee = fee;
    this.audienceCount = audienceCount;
  }
}
