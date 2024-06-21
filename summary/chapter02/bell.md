# Chapter02. 객체지향 프로그래밍

## 01 요구사항

## 02 객체지향 프로그래밍을 향해

### 협력, 객체, 클래스

- 객체지향 언어가 익숙한 사람이라면 가장 먼저 어떤 클래스가 필요한지 고민할 것이다. 대부분의 사람들은 클래스를 결정한 후에 클래스에 어떤 속성과 메소드가 필요한지 고민한다.
- <u>하지만 이것은 객체지향의 본질과는 거리가 멀다. 진정한 객체지향 패러다임으로의 전환은 클래스가 아닌 객체에 초점을 맞출 때에만 얻을 수 있다.</u>
    1. 어떤 클래스가 필요한지 고민하기 전에 어떤 객체들이 필요한지 고민하라. 클래스는 공통적인 상태와 행동을 공유하는 객체들을 추상화한 것이다. 따라서 클래스의 윤곽을 잡기 위해서는 어떤 객체들이 어떤 상태와 행동을 가지는지를 먼저 결정해야 한다. 객체를 중심에 두는 접근 방법은 설계를 단순하고 깔끔하게 만든다.
    2. 객체를 독립적인 존재가 아니라 기능을 구현하기 위해 협력하는 공동체의 일원으로 봐야 한다. 객체를 고립된 존재로 바라보지 말고 협력에 참여하는 협력자로 바라봐야 한다.
- 객체의 모양과 윤곽이 잡히면 공통된 특성과 상태를 가진 객체들을 타입으로 분류하고 이 타입을 기반으로 클래스를 구현하라.
- 훌륭한 협력이 훌륭한 객체를 낳고 훌륭한 객체가 훌륭한 클래스를 낳는다.

### 도메인 구조를 따르는 프로그램 구조

- 도메인이란 문제를 해결하기 위해 사용자가 프로그램을 사용하는 분야를 뜻한다.
- 일반적으로 클래스의 이름은 대응되는 도메인 개념의 이름과 동일하거나 적어도 유사하게 지어야 한다.
- 클래스의 관계도 최대한 도메인 개념 사이에 맺어진 관계와 유사하게 만들어서 프로그앰의 구조를 이해하고 예상하기 쉽게 만들어야 한다.

### 클래스 구현하기

- `상영`을 구현한 Screening 클래스를 작성해보자.

```java
public class Screening {

  private Movie movie;
  private int sequence;
  private LocalDateTime wheScreened;

  public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
    this.movie = movie;
    this.sequence = sequence;
    this.wheScreened = whenScreened;
  }

  public LocalDateTime getStartTime() {
    return wheScreened;
  }
  
  public boolean isSequence(int sequence) {
    return this.sequence == sequence;
  }
  
  public Money getMovieFee() {
    return movie.getFee();
  }
}
```

- 여기서 주목할 점은 인스턴스 변수의 가시성은 `private`이고, 메소드의 가시성은 `public`이라는 점이다.
    - 클래스를 구현하거나 다른 개발자에 의해 개발된 클래스를 사용할 때 가장 중요한 것은 클래스의 경계를 구분 짓는 것이다.
    - 어떤 부분을 외부에 공개하고 어떤 부분을 감출지 결정하는 것은 클래스의 설계의 핵심이다.
- 그렇다면 클래스의 내/외부를 구분해야 하는 이유는 무엇일까?
    - 경계의 명확성이 객체의 자율성을 보장해주기 때문이다. 또한 프로그래머에게 구현의 자유를 제공하기 때문이다.

### 자율적인 객체

- 먼저 2가지 중요한 사실을 인지하자.
    1. 객체가 **상태(state)**와 **행동(behavior)**을 함께 가지는 복합적인 존재라는 것
    2. 객체가 스스로 판단하고 행동하는 자율적인 존재라는 것
- 객체 지향은 객체라는 단위 안에 데이터와 기능을 한 덩어리로 묶음으로써 문제 영역의 아이디어를 적절하게 표현할 수 있게 했다. 이것을 **캡슐화**라고 한다.
- 또한 외부에서 접근을 통제할 수 있는 **접근 제어(access control)** 매커니즘도 함께 제공한다.
- 객체 내부에 대한 접근을 통제하는 이유는 객체를 자율적인 존재로 만들기 위해서다. 스스로 상태를 관리하고, 판단하고 행동적으로 자율적인 공동체를 구성하기 위함이다.
    - 그렇게 하기 위해선 외부의 간섭을 최소화해야 한다. 객체에서 원하는 것을 요청하고 객체가 스스로 최선을 방법을 결정할 수 있을 것이라는 점을 믿고 기다려야 한다.
- 캡슐화와 접근 제어는 객체를 두 부분으로 나눈다.
    - `퍼블릭 인터페이스(public interface)`: 외부에서 접근 가능한 부분
    - `구현(implementation)`: 내부에서만 접근 가능

### 프로그래머의 자유

- 프로그래머의 역할은 클래스 작성자와 클라이언트 프로그래머로 구분하는 것이 유용하다.
    - 클래스 작성자는 새로운 데이터 타입을 프로그램에 추가
    - 클라이언트 프로그래머는 클래스 작성자가 추가한 데이터 타입을 사용
- 클라이언트 프로그래머
    - 필요한 클래스를 엮어서 애플리케이션을 빠르고 안정적으로 구축하는 것이 목표
- 클래스 작성자
    - 클라이언트 프로그래머에게 필요한 부분만 공개하고 나머지는 꽁꽁 숨긴다.
    - 사용자가 마음대로 접근할 수 없게 방지함으로서 영향을 걱정하지 않고 내부 구현을 마음대로 변경할 수 있다. 이를 **구현 은닉(implementation hiding)**이라고 한다.

### 협력하는 객체들의 공동체

```java
public class Screening {
  
  ...
  
  public Reservation reserve(Customer customer, int audienceCount) {
    return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
  }

  private Money calculateFee(final int audienceCount) {
    return movie.calculateMovieFee(this).times(audienceCount);
  }
}
```

- 예전에 Long 타입을 사용해 금액을 구현했을 때는 변수의 크기나 연산자의 종류와 관련된 구현 관점의 제약을 표현할 수 있지만 Money 타입처럼 저장하는 값이 금액과 관련되어 있다는 의미를 전달할 수는 없었다.
- 또한 금액과 관련된 로직이 서로 다른 곳에 중복되어 구현되는 것을 막을 수 없다.
- 객체지향의 장점은 객체를 이용해 도메인의 의미를 풍부하게 표현할 수 있다는 것이다.
- 그 개념이 비록 인스턴스 변수만 포함하더라도 개념을 명시적으로 표현하는 것은 전체적인 설계의 명확성과 유연성을 높이는 첫걸음이다.

```java
public class Money {

  public static final Money ZERO = Money.wons(0);

  private final BigDecimal amount;

  public static Money wons(long amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  public static Money wons(double amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  public Money(final BigDecimal amount) {
    this.amount = amount;
  }

  public Money plus(Money amount) {
    return new Money(this.amount.add(amount.amount));
  }

  public Money minus(Money amount) {
    return new Money(this.amount.subtract(amount.amount));
  }

  public Money times(double percent) {
    return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
  }

  public boolean isLessThan(Money other) {
    return amount.compareTo(other.amount) < 0;
  }

  public boolean isGreaterThanOrEqual(Money other) {
    return amount.compareTo(other.amount) >= 0;
  }
}
```

- Reservation은 여러 속성을 포함한다.

```java
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
```

- 영화를 예매하기 위해 `Screening`, `Movie`, `Reservation` 인스턴스들은 서로의 메소드를 호출하며 상호작용한다.
- 이처럼 시스템의 어떤 기능을 구현하기 위해 객체들 사이에 이뤄지는 상호작용을 **협력(Collaboration)**이라고 부른다.
- 객체 지향 프로그램을 작성할 때는 먼저 협력의 관점에서 어떤 객체가 필요한지를 결정하고, 객체들의 공통 상태와 행위를 구현하기 위해 클래스를 작성한다.

### 협력에 관한 짧은 이야기

- 객체가 다른 객체와 상호작용할 수 있는 유일한 방법은 메시지를 전송(send a message)하는 것뿐이다.
- 다른 객체에게 요청이 도착할 때 해당 객체가 메시지를 수신(receive a message)했다고 이야기 한다.
    - 메시지를 수신한 객체는 스스로의 결정에 따라 자율적으로 메시지를 처리할 방법을 결정한다.
    - 이처럼 수신된 메시지를 처리하기 위한 자신만의 방법을 `메소드(method)`라고 부른다.
- 메시지와 메소드를 구분하는 것은 매우 중요하다.
    - 메소드와 메시지의 구분에서부터 `다형성(polymorphism)` 개념이 출발한다.
- `Screening`이 `Movie`의 `calculateMovieFee()` '메소드를 호출한다'라고 말했지만 사실은 `Screening`이 `Movie`에게 `calculateMovieFee` 메시지를 전송한다라고 말하는 것이 더 적절한 표현이다.
- 사실 `Screening`은 `Movie` 안에 `calculateMovieFee` 메소드가 존재하고 있는지조차 알지 못한다. 단지 `Movie`가 `calculateMovieFee` 메시지에 응답할 수 있다고 믿고 메시지를 전송할 뿐이다.
- 이제 메시지를 처리하는 것은 온전히 `Movie`의 몫이다.

<br/>

## 03 할인 요금 구하기

- `Movie`를 살펴보니 그 어디에도 어떤 할인 정책을 사용할 것인지 결정하는 코드가 없다. (금액 할인 vs 비율 할인)
- 여기에는 `상속(ingeritance)`, `다형성`. `추상화(abstraction)`라는 원리가 숨겨져 있다.

```java
public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Movie(final String title, final Duration runningTime, final Money fee,
      final DiscountPolicy discountPolicy) {
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountPolicy = discountPolicy;
  }

  public Money getFee() {
    return fee;
  }

  public Money calculateMovieFee(Screening screening) {
    return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```

### 할인 정책과 할인 조건

- `DiscountPolicy`은 추상 클래스로 구현했다.
    - 중복 코드는 부모클래스인 `DiscountPolicy`이 가지도록 한다.
    - 두 가지 할인 정책(`AmountDiscountPolicy`, `PercentDiscountPolicy`)은 부모클래스를 상속받아 구현할 것이다.
    - 부모에서 기본적인 알고리즘 흐름을 구현하고 중간에 필요한 처리를 자식 클래스에게 위임하는 디자인 패턴을 `TEMPLATE_METHOD` 패턴이라고 부른다.
- 하나의 할인 정책은 여러 개의 할인 조건을 포함할 수 있다.
- 만약 만족하는 할인 조건이 하나도 없다면 할인 금액으로 `Money.ZERO`를 반환해 원래의 영화 가격을 내도록 한다.

```java
public abstract class DiscountPolicy {

  private List<DiscountCondition> conditions = new ArrayList<>();

  public DiscountPolicy(DiscountCondition ... conditions) {
    this.conditions = Arrays.asList(conditions);
  }

  public Money calculateDiscountAmount(Screening screening) {
    for (DiscountCondition condition: conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return getDiscountAmount(screening);
      }
    }
    return Money.ZERO;
  }

  abstract protected Money getDiscountAmount(final Screening screening);
}
```

- 이번엔 할인 조건이다. 순번(sequence)와 상영시간 할인 조건을 각각 구현한다.

```java
public interface DiscountCondition {
  boolean isSatisfiedBy(Screening screening);
}
```

```java
public class SequenceCondition implements DiscountCondition{

  private int sequence;

  public SequenceCondition(final int sequence) {
    this.sequence = sequence;
  }

  @Override
  public boolean isSatisfiedBy(final Screening screening) {
    return screening.isSequence(sequence);
  }
}
```

```java
public class PeriodCondition implements DiscountCondition {

  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public PeriodCondition(final DayOfWeek dayOfWeek, final LocalTime startTime,
      final LocalTime endTime) {
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean isSatisfiedBy(final Screening screening) {
    return screening.getStartTime().getDayOfWeek().equals(dayOfWeek)
        && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0
        && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
  }
}
```

- 이제 할인 정책을 구현할 차례다.

```java
public class AmountDiscountPolicy extends DiscountPolicy {

  protected Money discountAmount;

  public AmountDiscountPolicy(final Money discountAmount, final DiscountCondition... conditions) {
    super(conditions);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money getDiscountAmount(final Screening screening) {
    return discountAmount;
  }
}
```

```java
public class PercentDiscountPolicy extends DiscountPolicy {

  private double percent;

  public PercentDiscountPolicy(final double percent, final DiscountCondition... conditions) {
    super(conditions);
    this.percent = percent;
  }

  @Override
  protected Money getDiscountAmount(final Screening screening) {
    return screening.getMovieFee().times(percent);
  }
}
```

### 할인 정책 구성하기

- `하나의 영화 대해 단 하나의 할인 정책만 설정할 수 있지만 할인 조건의 경우에는 여러 개를 적용할 수 있다`는 요구사항
    - 위의 코드를 보면 잘 구현되어 있다는 사실을 알 수 있다.
    - `Movie`와 `DiscountPolicy`의 생성자는 이런 제약을 강제한다.
    - `Movie`의 생성자는 오직 하나의 `DiscountPolicy` 인스턴스만 받을 수 있도록 선언되어 있다.
    - 반면 `DiscountPolicy` 생성자는 여러 개의 `DiscountCondition` 인스턴스를 허용한다.

<br/>

## 04 상속과 다형성

### 컴파일 시간 의존성과 런타임 의존성

- 코드의 의존성과 실행 시점의 의존성이 서로 다를 수 있다. 다시 말해 클래스 사의 의존성과 객체 사이의 의존성은 동일하지 않을 수 있다.
- 코드의 의존성과 실행 시점의 의존성은 트레이드오프를 가진다.
    - 의존성이 다를수록 코드를 이해하기 어려워진다. 코드를 이해하기 위해 코드뿐만 아니라 객체를 생성하고 연결하는 부분을 찾아야 하기 때문이다.
    - 하지만 다를수록 코드는 더 유연해지고 확장 가능해진다.
- 설계가 유연해질수록 코드를 이해하고 디버깅하기는 점점 더 어려워진다는 사실을 기억하라. 항상 유연성과 가독성 사이에서 고민해야 한다.

### 차이에 의한 프로그래밍

- 상속은 코드를 재사용하기 위해 널리 사용되는 방법이다.
- 부모 클래스와 다른 부분만을 추가해서 새로운 클래스를 쉽고 빠르게 만드는 방법을 `차이에 의한 프로그래밍(programming by difference)` 라고 한다.

### 상속과 인터페이스

- 상속이 가치 있는 이유는 부모 클래스가 제공하는 모든 인터페이스를 자식 클래스가 물려받을 수 있기 때문이다.
    - 인터페이스는 객체가 이해할 수 있는 메시지의 목록을 정의한다는 것을 기억하라.
    - 상속을 통해 자식 클래스는 자신의 인터페이스에 부모 클래스의 인터페이스를 포함하게 된다.
- 컴파일러는 코드 상에서 부모 클래스가 나오는 모든 장소에서 자식 클래스를 사용하는 것을 허용한다.
    - 이처럼 자식 클래스가 부모 클래스를 대신하는 것을 업캐스팅(upcasting)이라고 한다.

### 다형성

- 다시 한번 강조하지만 메시지와 메소드는 다른 개념이다.
    - `Movie`는 `DiscountPolicy`에게 메시지를 전송하지만 실행 시점에 실제로 실행되는 메소드는 `Movie`와 협력하는 객체의 실제 클래스가 무엇인지에 따라 달라진다. (`AmountDiscountPolicy`, `PercentDiscountPolicy`)
    - 이를 **다형성**이라고 부른다.
- 다형성은 컴파일타임 의존성과 런타임 의존성이 다를 수 있다는 사실을 기반으로 한다.
    - 다형성은 동일한 메시지를 수신했을 때 객체의 타입에 따라 다른 응답을 보낼 수 있는 능력이다.
    - 따라서 다형적인 협력에 참여하는 객체들은 모두 같은 메시지를 이해할 수 있어야 한다. 다시 말해 인터페이스 동일해야 한다는 것이다.
- 메시지에 응답하기 위해 실행될 메소드를 **런타임**에 바인딩하는 것을 `지연 바인딩(lazy binding)` 또는 `동적 바인딩(dynamic binding)`이라고 부른다.
- 반면 전통적인 함수처럼 **컴파일 시점**에 실행될 함수나 프로시저를 결정하는 것을 `초기 바인딩(early binding)` 또는 `정적 바인딩(static binding)`이라고 한다.


> 구현 상속과 인터페이스 상속
> - 구현 상속을 서브클래싱(subclassing): 순수하게 코드를 재사용하기 위한 목적
> - 인터페이스 상속을 서브타이핑(subtyping): 다형적인 협력을 위해 인터페이스를 공유하기 위한 목적
> - 인터페이스 재사용이 아닌 구현을 재사용 목적으로 상속을 사용하면 변경에 취약한 코드를 낳게 될 확률이 높다.

### 인터페이스와 다형성

- C#과 자바는 인터페이스라는 프로그래밍 요소를 제공한다.
- 자바의 인터페이스는 구현에 대한 고려 없이 다형적인 협력에 참여하는 클래스들이 공유 가능한 외부 인터페이스를 정의한 것이다.

<br/>

## 05 추상화와 유연성

### 추상화의 힘

- 할인 정책은 구체적인 금액 할인과 비율 할인을 포괄하는 추상적인 개념이다.
- 할인 조건은 더 구체적인 순번 조건과 기간 조건을 포괄하는 추상적인 개념이다.
- `DiscountPolicy`와 `DiscountCondition`을 통해 추상화의 장점을 살펴보자.
1. 추상화의 계층만 따로 떼어 놓고 살펴보면 요구사항의 정책을 높은 수준에서 서술할 수 있다는 점
2. 추상화를 이용하면 설계가 좀 더 유연해진다는 점

- 추상화를 사용하면 세부적인 내용을 무시한 채 상위 정책을 쉽고 간단하게 표현할 수 있다. 추상화의 이런 특징은 세부사항에 억눌리지 않고 상위 개념만으로도 도메인의 중요한 개념을 설명할 수 있게 한다.
- 재사용 가능한 설계의 기본을 이루는 `디자인 패턴(design pattern)`이나 `프레임워크(framework)` 모두 추상화를 이용해 상위 정책을 정의하는 객체지향의 매커니즘을 활용하고 있다.

### 유연한 설계

- 할인 정책이 적용되지 않는 경우도 추가해보자.

```java
public class Movie {
  ...

  public Money calculateMovieFee(Screening screening) {
    if (discountPolicy == null) {
      return fee;
    }
    return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```

- 위와 같은 방식의 문제점은 할인 정책이 없는 경우를 예외 케이스로 취급하기 때문에 지금까지 일관성 있던 협력 방식이 무너지게 된다는 것이다.
- 기존 할인 정책의 경우 할인할 금액을 계산하는 책임이 `DiscountPolicy`의 자식 클래스에 있었지만 할인 정책이 없는 경우에는 계산 책임이 `Movie` 쪽이 있기 때문이다.
- 일관성을 지키는 방법은 할인 요금의 책임을 그대로 `DiscountPolicy` 계층에 유지시키는 것이다.

```java
public class NoneDiscountPolicy extends DiscountPolicy {

  @Override
  protected Money getDiscountAmount(final Screening screening) {
    return Money.ZERO;
  }
}
```

- 중요한 것은 기존의 `Movie`, `DiscountPolicy`를 수정하지 않고 `NoneDiscountPolicy`라는 새로운 클래스를 추가하는 것만으로 애플리케이션의 기능을 확장했다는 것이다.

```java
Movie starWars = new Movie(
        "스타워즈",
        Duration.ofMillis(210),
        Money.wons(10000),
        new NoneDiscountPolicy());
```

### 추상 클래스와 인터페이스 트레이드오프

- `NoneDiscountPolicy`의 경우, 무조건 0원이 반환되기 때문에 사실 `getDiscountAmount` 메소드를 호출할 필요가 없다.
- `DiscountPolicy`를 인터페이스로 변경하고 그 인터페이스를 구현하는 추상클래스인 `DefaultDiscountPolicy`를 둔다.
    - `NoneDiscountPolicy`는 인터페이스를 구현한다.
    - 다른 정책은 `DefaultDiscountPolicy`을 상속받는다.
- 어떤 설계가 더 좋을까?
    - 이상적으로는 인터페이스를 추가한 설계가 나을 것이다.
    - 현실적으로는 `NoneDiscountPolicy`만을 위해 인터페이스를 추가하는 것이 과하다는 생각이 들 수 있다. 어쨌든 변경 전의 클래스가 할인 금액이 0원이라는 사실을 효과적으로 전달하기 때문이다.
- 사실 구현과 관련된 모든 것은 트레이드오프의 대상이 될 수 있다.
    - 우리가 작성하는 모든 코드에는 합당한 이유가 있어야 한다.
    - 사소한 결정이더라도 트레이드오프를 통해 얻어진 결론과 그렇지 않은 결론 사이의 차이가 크다.

### 코드 재사용

- 코드 재사용을 위해 상속보다는 `합성(composition)`이 더 좋은 방법이라는 얘기가 있다.
- **합성**은 다른 객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 재사용하는 것을 말한다.
- `Movie`가 `DiscountPolicy`를 사용하는 방식이 합성이다.

```java
public class Movie {

  private String title;
  private Duration runningTime;
  private Money fee;
  private DiscountPolicy discountPolicy;
}
```

### 상속

- 상속은 2가지 관점에서 설계에 안 좋은 영향을 미친다.
1. 상속이 캡슐화를 위반한다.
2. 설계를 유연하지 못하게 만든다.

- 상속을 이용하기 위해서는 부모 클래스의 내부 구조를 잘 알고 있어야 한다.
    - 부모 클래스의 구현이 자식 클래스에 노출되기 때문에 캡슐화가 약해진다.
    - 캡슐화의 약화는 자식 클래스가 부모 클래스에 강하게 결합되도록 만들기 때문에 부모 클래스를 변경할 때 자식 클래스도 함께 변경될 확률을 높인다.
    - 결과적으로 상속을 과도하게 사용하면 변경하기 어려운 코드가 되어버린다.
-  상속은 부모 클래스와 자식 클래스의 관계를 컴파일 시점에 정해버린다.
- `Movie`는 컴포지션을 통해 할인 정책을 사용하기 때문에 discountPolicy를 변경할 수 있는 메소드를 추가할 수 있다.
- 만약 `AmountDiscountMovie`, `PercentDiscountMovie`와 같이 할인 정책이 상속 관계였다면 변경이 어려웠을 것이다.

```java
public class Movie {
  ...
  
  public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }
}
```

### 합성 Composition

- 인터페이스에 정의된 메시지를 통해서만 코드를 재사용하는 방법을 합성이라고 한다.
- 합성은 상속이 가지는 두 가지 문제점을 모두 해결한다.
1. 구현을 효과적으로 캡슐화할 수 있다.
2. 의존하는 인스턴스 교체가 비교적 쉽기 때문에 설계가 유연해진다.

- 상속이 클래스를 통해 강하게 결합되는 것에 비해 합성을 메시지를 통해 느슨하게 결합된다.
- 따라서 코드 재사용을 위해서라면 상속보다는 합성을 사용하는 것이 더 좋은 방법이다.
- 그렇다고 절대 상속을 사용하지 말라는 것은 아니다. 다형성을 위해 인터페이스를 재사용하는 경우 상속과 합성을 함께 조합해서 사용할 수밖에 없다.
