package chapter12.bell.reuse;

import java.util.List;
import java.util.stream.Collectors;

public class GradeLecture extends Lecture {
  private List<Grade> grades;

  public GradeLecture(final String title, final int pass, final List<Integer> scores,
      final List<Grade> grades) {
    super(title, pass, scores);
    this.grades = grades;
  }

  @Override
  public String evaluate() {
    return super.evaluate() + ", " + gradesStatistics();
  }

  private String gradesStatistics() {
    return grades.stream()
        .map(this::format)
        .collect(Collectors.joining(" "));
  }

  private String format(Grade grade) {
    return String.format("%s:%d", grade.getName(), gradeCount(grade));
  }

  private long gradeCount(final Grade grade) {
    return getScores().stream()
        .filter(grade::include)
        .count();
  }

  public double average(String gradeName) {
    return grades.stream()
        .filter(each -> each.isName(gradeName))
        .findFirst()
        .map(this::gradeAverage)
        .orElse(0d);
  }

  private double gradeAverage(final Grade grade) {
    return getScores().stream()
        .filter(grade::include)
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0);
  }
}
