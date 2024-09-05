package chapter12.bell.reuse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {
  private String title;
  private int pass;
  private List<Integer> scores = new ArrayList<>();

  public Lecture(final String title, final int pass, final List<Integer> scores) {
    this.title = title;
    this.pass = pass;
    this.scores = scores;
  }

  public double average() {
    return scores.stream()
        .mapToInt(Integer::intValue)
        .average().orElse(0);
  }

  public List<Integer> getScores() {
    return Collections.unmodifiableList(scores);
  }

  public String evaluate() {
    return String.format("Pass:%d Fail:%d", passCount(), failCount());
  }

  private long passCount() {
    return scores.stream().filter(score -> score >= pass).count();
  }

  private long failCount() {
    return scores.size() - passCount();
  }
}
