package chapter12.bell.reuse;

public class Grade {
  private String name;
  private int upper, lower;

  public Grade(final String name, final int upper, final int lower) {
    this.name = name;
    this.upper = upper;
    this.lower = lower;
  }

  public String getName() {
    return name;
  }

  public boolean isName(String name) {
    return this.name.equals(name);
  }

  public boolean include(int score) {
    return score >= lower && score <= upper;
  }
}
