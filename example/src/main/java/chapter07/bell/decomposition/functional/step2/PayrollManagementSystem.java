package chapter07.bell.decomposition.functional.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PayrollManagementSystem {

  static List<String> employees = new ArrayList<>(
      Arrays.asList("직원A", "직원B", "직원C", "아르바이트D", "아르바이트E", "아르바이트F"));
  static List<Long> basePays = new ArrayList<>(Arrays.asList(400L, 300L, 250L, 1L, 1L, 2L));
  static List<Boolean> hourlys = new ArrayList<>(
      Arrays.asList(false, false, false, true, true, true));
  static List<Integer> timeCards = new ArrayList<>(Arrays.asList(0, 0, 0, 120, 120, 120));

  public void main(String operation, String[] args) {
    switch (operation) {
      case "pay":
        calculatePay(args[0]);
        return;
      case "basePays":
        sumOfBasePays();
        return;
    }
  }

  void sumOfBasePays() {
    long result = 0;
    for (String name: employees) {
      if (isHourly(name)) {
        continue;
      }
      result += basePays.get(employees.indexOf(name));
    }
    System.out.println(result);
  }

  void calculatePay(String name) {
    int taxRate = getTaxRate();
    long pay = 0;
    if (isHourly(name)) {
      pay = calculateHourlyPayFor(name, taxRate);
    } else {
      pay = calculatePayFor(name, taxRate);
    }
    System.out.println(describeResult(name, pay));
  }

  int getTaxRate() {
    System.out.println("세율을 입력하세요: ");
    final Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  boolean isHourly(String name) {
    return hourlys.get(employees.indexOf(name));
  }

  long calculateHourlyPayFor(String name, int taxRate) {
    int index = employees.indexOf(name);
    long basePay = basePays.get(index) * timeCards.get(index);
    return basePay - (basePay * taxRate / 100);
  }

  long calculatePayFor(String name, int taxRate) {
    int index = employees.indexOf(name);
    Long basePay = basePays.get(index);
    return basePay - (basePay * taxRate / 100);
  }

  String describeResult(String name, long pay) {
    return String.format("이름: %s, 급여: %d", name, pay);
  }
}
