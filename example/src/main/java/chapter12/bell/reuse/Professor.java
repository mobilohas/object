package chapter12.bell.reuse;

public class Professor {

  private String name;
  private Lecture lecture;

  public Professor(final String name, final Lecture lecture) {
    this.name = name;
    this.lecture = lecture;
  }

  public String compileStatistics() {
    return String.format("[%s] %s - Avg: %.1f", name, lecture.evaluate(), lecture.average());
  }
}
