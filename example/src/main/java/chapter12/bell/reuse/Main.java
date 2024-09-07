package chapter12.bell.reuse;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Lecture lecture = new Lecture("객체지향 프로그래밍", 70, Arrays.asList(81, 95, 75, 50, 45));
    System.out.println(lecture.evaluate());

    GradeLecture gradeLecture = new GradeLecture(
        "객체지향 프로그래밍",
        70,
        Arrays.asList(81, 95, 75, 50, 45),
        Arrays.asList(
            new Grade("A", 100, 95),
            new Grade("B", 94, 80),
            new Grade("C", 79, 70),
            new Grade("D", 69, 50),
            new Grade("F", 49, 0)));
    System.out.println(gradeLecture.evaluate());

    Professor professorLecture = new Professor(
        "다익스트라", new Lecture("알고리즘", 70, Arrays.asList(81, 95, 75, 50, 45)));
    System.out.println(professorLecture.compileStatistics());

    Professor professorGradeLecture = new Professor(
        "다익스트라",
        new GradeLecture(
            "객체지향 프로그래밍",
            70,
            Arrays.asList(81, 95, 75, 50, 45),
            Arrays.asList(
                new Grade("A", 100, 95),
                new Grade("B", 94, 80),
                new Grade("C", 79, 70),
                new Grade("D", 69, 50),
                new Grade("F", 49, 0))));
    System.out.println(professorGradeLecture.compileStatistics());
  }
}
