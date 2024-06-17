# Chapter01 객체, 설계

- 이론이 먼저일까? 실무가 먼저일까? 로버트 L. 글래스는 어떤 분야를 막론하고 이론을 정립할 수 없는 초기에는 실무가 먼저 급속한 발전을 이룬다고 한다.
    - (개인적인 생각) 맞는 것 같다. 실무를 하기 전까지만 해도 이론서를 읽어봤자, 이해도 안 되고 이게 뭐지? 싶었다.
    - 예를 들어, 실무를 하기 전에 클린 코드를 2번 읽었지만 느껴지는 것도 많이 없었고 실제 코드에 적용도 제대로 안 했던 것 같은데, 로하스밀 이후에는 문장 하나하나가 와닿는다.
- 실무가 어느정도 발전하고 나서야  비로소 실무의 실용성을 입증할 수 있는 이론이 서서히 모습을 갖춰가고 해당 분야가 충분히 성숙하는 시점에ㅅ 이론이 실무를 추월하게 된다.
- 실무가 이론보다 앞서는 분야. 소프트웨어 `설계`와 `유지보수`
- 대부분의 설계 원칙과 개념은 실무에서 반복적으로 적용되던 기법들을 이론화한 것이 대부분이다.
    - 예를 들면, 디자인 패턴 등이 있을 듯

<br/>

## 1. 티켓 판매 애플리케이션 구현하기

### 요구사항

- 모든 관람은 티켓이 있어야 입장이 가능하다.
- 이벤트에 당첨된 관람객과 그렇지 못한 관람객은 다른 방식으로 입장
    - 이벤트에 당첨된 관람객은 초대장을 티켓으로 교환한 후에 입장 가능
    - 당첨되지 않은 관람객은 티켓을 구매해야 입장 가능

### 코드

- 이벤트 당첨자에게 전달될 초대장

```java
public class Invitation {
  private LocalDateTime when;
}
```

- 공연을 관람하기 위한 티켓

```java
public class Ticket {
  private Long fee;
  
  public Long getFee() {
    return fee;
  }
}
```

- 관람객의 소지품을 보관할 용도로 가져온 가방

```java
public class Bag {

  private Long amount;
  private Invitation invitation;
  private Ticket ticket;
  
  public Bag(long amount) {
    this(null, amount);
  }
  
  public Bag(Invitation invitation, long amount) {
    this.invitation = invitation;
    this.amount = amount;
  }

  public boolean hasInvitation() {
    return invitation != null;
  }

  public boolean hasTicket() {
    return ticket != null;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }
  
  public void minusAmount(Long amount) {
    this.amount -= amount;
  }
  
  public void plusAmount(Long amount) {
    this.amount += amount;
  }
}
```

- 관람객

```java
public class Audience {
  private Bag bag;
  
  public Audience(Bag bag) {
    this.bag = bag;
  }
  
  public Bag getBag() {
    return this.bag;
  }
}
```

- 매표소

```java
public class TicketOffice {
  private Long amount;
  private List<Ticket> tickets = new ArrayList<>();
  
  public TicketOffice(Long amount, Ticket ...tickets) {
    this.amount = amount;
    this.tickets.addAll(Arrays.asList(tickets));
  }
  
  public Ticket getTicket() {
    return tickets.remove(0);
  }
  
  public void minusAmount(Long amount) {
    this.amount -= amount;
  }
  
  public void plusAmount(Long amount) {
    this.amount += amount;
  }
}
```

- 판매원

```java
public class TicketSeller {
  private TicketOffice ticketOffice;
  
  public TicketSeller(TicketOffice ticketOffice) {
    this.ticketOffice = ticketOffice;
  }
  
  public TicketOffice getTicketOffice() {
    return this.ticketOffice;
  }
}
```

- 소극장

```java
public class Theater {
  private TicketSeller ticketSeller;
  
  public Theater(TicketSeller ticketSeller) {
    this.ticketSeller = ticketSeller;
  }
  
  public void enter(Audience audience) {
    if (audience.getBag().hasInvitation()) {
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();
      audience.getBag().setTicket(ticket);
    } else {
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();
      audience.getBag().minusAmount(ticket.getFee());
      ticketSeller.getTicketOffice().pulsAmount(ticket.getFee());
      audience.getBag().setTicket(ticket);
    }
  }
}
```

<br/>

## 2. 무엇이 문제인가?

> 모든 소프트웨어 모듈에는 세 가지 목적이 있다.
> 1. 실행 중에 제대로 동작하는 것
> 2. 변경을 위해 존재하는 것
> 3. 코드를 읽는 사람과 의사소통하는 것
> - 로버트 마틴 <클린 소프트웨어: 애자일 원칙과 패턴, 그리고 실천 방법>

- 위 코드는 몇가지 문제를 가지고 있다.
- 필요한 기능을 오류 없이 정확하게 수행하고 있지만 변경 용이성과 읽는 사람과의 의사소통이라는 목적은 만족시키지 못한다.

### 예상을 빗나가는 코드

- `Theater`의 `enter()` 메소드는 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재라는 전제가 갈려있다.
    - 소극장이 초대장을 확인하기 위해 관람객의 가방을 열어본다??
    - 소극장이 판매원의 허락 없이 매표소에 보관 중인 티켓과 현금에 마음대로 접근할 수 있다??
    - 이런 방식은 우리의 상식과 너무 다르게 동작하기 때문에 코드를 읽는 사람과 제대로 의사소통하지 못한다고 볼 수 있다.
    - 또, 소극장은 현재 상황을 통제하기 위해 너무 많은 객체 정보를 알고 있어야 한다.(Audience가 Bag을 가지고 있고 Bag에는 무엇이 들어 있는지, 그 외에도 TicketOffice와 TicketSeller의 상세 정보에 대해 알고 있어야 한다.)
    - 각각의 객체가 자기 할 일을 하게 만들면 어떨까?

### 변경에 취약한 코드

- 더 큰 문제는 해당 코드가 변경에 취약하다는 점이다.
    - Audience와 TicketSeller를 변경할 겨우 Theater가 변경되어야만 한다.
- 이것은 객체 사이의 의존성(dependency)와 관련된 문제다.
    - 의존성은 변경에 대한 영향을 암시한다. -> 어떤 객체가 변경되면 그 객체에게 의존하고 있는 다른 객체도 변경되어야 한다는 의미.
- 의존성을 전부 없애는 것이 정답은 아니다. 다만 기능을 구현하는 데 필요한 최소한의 의존성만 유지하고 불필요한 의존성을 제거하는 작업이 필요하다.
- Theater는 현재 아래 객체에 의존하고 있다. (너무 많은 객체에 의존하고 있다.)
    - Audience
    - Bag
    - TicketOffice
    - TicketSeller
    - Ticket
- 이렇게 의존성이 과한 경우를 가리켜 결합도(coupling)가 높다고 한다. -> 설계의 목표는 객체 사이의 결합도를 낮춰 변경이 용이한 방향으로 나아가는 것!

<br/>

## 3. 설계 개선하기

- 위 문제의 해결방법은 간단하다. Theater와 Audience와 TicketSeller에 관해 너무 세세한 부분까지 알지 못하도록 정보를 차단하면 된다.
- 그리고 관람객과 판매원을 자율적인 존재로 만들면 된다.

### 자율성 높이기

- 티켓 판매원이 직접 티켓을 팔 수 있도록 자율성을 부여해보자. (Theater의 `enter()`에 있는 로직을 떼어와 넣을 것이다.)

```java
public class TicketSeller {
  private TicketOffice ticketOffice;
  
  public TicketSeller(TicketOffice ticketOffice) {
    this.ticketOffice = ticketOffice;
  }
  
  public void sellTo(Audience audience) {
    if (audience.getBag().hasInvitation()) {
      Ticket ticket = ticketOffice.getTicket();
      audience.getBag().setTicket(ticket);
    } else {
      Ticket ticket = ticketOffice.getTicket();
      audience.getBag().minusAmount(ticket.getFee());
      ticketOffice.pulsAmount(ticket.getFee());
      audience.getBag().setTicket(ticket);
    }
  }
}
```

- 소극장은 덕분에 TicketOffice에 대한 의존성을 떼어낼 수 있게 되었다.
    - Theater가 TicketSeller라는 `interface`에만 의존하게 되었기 때문이다.

```java
public class Theater {
  private TicketSeller ticketSeller;
  
  public Theater(TicketSeller ticketSeller) {
    this.ticketSeller = ticketSeller;
  }
  
  public void enter(Audience audience) {
    ticketSeller.sellTo(audience);
}
```

- 이 다음은 Audience의 캡슐화이다. TicketSeller의 `sellTo()`에 있는 로직을 떼어올 것이다.
- 책임이 분산되면서 이해하기 쉽고 유연한 설계로 바뀌게 되었다!
    - Audience나 TicketSeller의 내부가 변경되더라도 Theater가 변경할 필요가 사라졌다!

```java
public class Audience {
  private Bag bag;
  
  public Audience(Bag bag) {
    this.bag = bag;
  }
  
  public Long buy(Ticket ticket) {
    if (bag.hasInvitation()) {
      bag.setTicket(ticket);
      return 0L;
    } else {
      bag.setTicket(ticket);
      bag.minusAmount(ticket.getFee());
      return ticket.getFee();
    }
  }
}
```

```java
public class TicketSeller {
  private TicketOffice ticketOffice;
  
  public TicketSeller(TicketOffice ticketOffice) {
    this.ticketOffice = ticketOffice;
  }
  
  public void sellTo(Audience audience) {
    ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
  }
}
```

### 캡슐화와 응집도

- 핵심은 객체 내부의 상태를 캡슐화하고 객체 간에 오직 메시지를 통해서만 상호작용하도록 만드는 것이다.
- 설계가 변경되면서 아래와 같은 변화가 생겼다.
    - Theater는 TicketSeller의 내부에 대해 알지 못한다. 다만, `sellTo` 메시지를 이해하고 응답할 수 있다는 사실만 알고 있을 뿐이다.
    - TicketSeller 역시 Audience의 내부에 대해 전혀 알지 못한다. 단지 Audience의 `buy` 메시지에 응답할 수 있고 자신이 원하는 결과를 반환할 것이라는 사실만 알고 있다.
- 밀접하게 연관된 작업만 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 것을 가리켜 응집도(cohesion)가 높다고 한다.
- 응집도가 높고 결합도가 낮은 애플리케이션을 만드는 것이 좋은 설계.

### 절차지향과 객체지향

- 절차지향 프로그래밍은 **프로세스**(Theater의 `enter`)와 **데이터**(Audience, Bag, TicketSeller, TicketOffice)를 별도의 모듈에 위치시키는 방식이다.
- 개선 전의 코드는 절차지향적 코드라고 볼 수 있다.
    - 절차지향 프로그래밍은 우리의 직관에 위배되는 것을 확인할 수 있다.
    - 데이터의 변경으로 인한 영향을 지역적으로 고립시키기가 어렵다는 문제가 있다는 것도 알았다. (Audience가 변경되면 Theater도 변경되어야 한다.)
    - 절차지향 프로그래밍은 변경에 취약하다.
- 변경하기 쉬운 설계는 한 번에 하나의 클래스만 변경할 수 있는 설계다.
- 객체지향 프로그래밍은 데이터와 프로세스가 동일한 모듈 내부에 위치하도록 프로그래밍하는 것을 말한다.
    - 캡슐화를 이용해 의존성을 적절히 관리함으로써 객체 사이의 결합도를 낮출 수 있다.
    - 캡슐화가 적용된 코드는 객체 내부의 변경이 객체 외부에 파급되지 않도록 제어할 수 있기 때문에 변경하기가 수월하다.

### 책임의 이동 Shift Of Responsibility

- 절차지향 프로그래밍은 책임이 한 곳에 집중되어 있다.
- 객체지향 프로그래밍은 책임을 분산한다. 각 객체가 자신을 스스로 책임진다.
    - 객체가 어떤 데이터를 가지느냐보다는 객체에 어떤 책임을 할당할 것인가에 초점!

### 더 개선할 수 있다

- Audience가 가지고 있는 Bag를 개선해보자. Bag도 자율적인 존재로 변경해볼 것이다.
- 코드 변경 후에는 Audience가 Bag의 구현(`hasInvitation`, `setTicket`, `minusAmount`)이 아닌 인터페이스(`hold`)에 의존하게 되었다는 사실

```java
public class Bag {

  private Long amount;
  private Ticket ticket;
  private Invitation invitation;

  public long hold(Ticket ticket) {
    if (hasInvitation()) {
      setTicket(ticket);
      return 0L;
    } else {
      setTicket(ticket);
      minusAmount(ticket.getFee());
      return ticket.getFee();
    }
  }
  
  private void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }
  
  private boolean hasInvitation() {
    return invitation != null;
  }
  
  private void minusAmount(Long amount) {
    this.amount -= amount;
  }
}
```

```java
public class Audience {
  
  ...
  
  public Long buy(Ticket ticket) {
    return bag.hold(ticket);
  }
}
```

- TicketOffice도 자율적인 존재로 만들 수 있다.
- 그런데 오히려 TicketOffice에 Audience 의존성이 추가된다???
    - TicketOffice로 책임 분산 vs Audience 결합도 낮추기
    - 결국 설계는 트레이드오프의 산물이다. 어떤 경우에도 모든 사람을 만족시킬 수 있는 설계는 없다.

```java
public class TicketOffice {
  
  ...
  
  public void sellTicketTo(Audience audience) {
    plusAmount(audience.buy(getTicket()));
  }
  
  private Ticket getTicket() {
    return tickets.remove(0);
  }
  
  private void plusAmount(Long amount) {
    this.amount += amount;
  }
}
```

```java
public class TicketSeller {
  
  ...
  
  public void sellTo(Audience audience) {
    ticketOffice.sellTicketTo(audience);
  }
}
```

### 객체의 자율성

- 현실 세계에선 수동적인 객체들
- 객체지향의 세계에 들어온 순간부터는 능동성이 부여된 존재로 새롭게 태어난다.
- 자율적인 존재로 소프트웨어 객체를 설계하는 원칙을 의인화(anthropomorphism)이라고 한다.


## 정리

- 셜계는 구현과 뗄레야 뗄 수 없는 관계
- 좋은 설계란? 기한 안에 기능을 구현할 수 있고, 변경 가능성이 용이한 코드를 만들 수 있는 것!
    - 요구사항 변경은 필연적으로 코드 수정을 초래하고 코드 수정은 버그를 발생시킬 가능성을 높인다. -> 버그는 또 코드 수정에 대한 두려움을 만들어 개발자의 의지를 꺾어 버린다.
    - 좋은 설계는 변경에 유연하게 대응할 수 있는 코드! 통제 가능하고 예측 가능한 코드를 만들어 버그 발생 위험을 줄이는 것!
- 객체들 사이의 의존성을 적절히 조절하는 것의 설계의 핵심 포인트
- 캡슐화? 구현은 감추고, 밖에서 사용할 인터페이스만 노출하는 것.
