package chapter07.bell.decomposition.functional.start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PayrollManagementSystem {

  static List<String> employees = new ArrayList<>(Arrays.asList("A", "B", "C"));
  static List<Long> basePays = new ArrayList<>(Arrays.asList(400L, 300L, 250L));

  public void main(String name) {
    int taxRate = getTaxRate();
    long pay = calculatePayFor(name, taxRate);
    print(describeResult(name, pay));
  }

  int getTaxRate() {
    System.out.println("세율을 입력하세요: ");
    final Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  long calculatePayFor(String name, int taxRate) {
    int index = employees.indexOf(name);
    Long basePay = basePays.get(index);
    return basePay - (basePay * taxRate / 100);
  }

  String describeResult(String name, long pay) {
    return String.format("이름: %s, 급여: %d", name, pay);
  }

  private void print(final String result) {
    System.out.println(result);
  }
}
