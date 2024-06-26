# Chapter02. 객체지향 프로그래밍

## 01 요구사항

## 02 객체지향 프로그래밍을 향해

### 인터페이스와 구현의 분리
인스턴스 변수는 private(=구현)
메서드는 public(=public interface) -> 객체의 자율성을 보장하기 위해서!
인터페이스와 구현의 분리!

### 프로그래머의 자유
프로그래머의 역할은 클래스 작성자와 클라이언트 프로그래머이다. 
구현 은닉을 통해서 클라이언트 프로그래머는 알아야 하는 정보량이 적어져서 좋다. 
클래스 작성자는 인터페이스만 바꾸지 않는다면, 신경쓰지 않아도 되서 좋다. 

설계가 필요한 이유는 `변경을 관리`하기 위해서이다.

### 협력에 관한 짧은 이야기
객체는 메세지를 보낼 수만 있다. 메세지를 수신한 객체는 결정에 따라 자율적으로 메세지를 처리한다. 

## 할인 요금 구하기
### 할인 요금 계산을 위한 협력 시작하기
### 할인 정책과 할인 조건
템플릿 패턴

### 할인 정책 구성하기

생성자의 파라미터 목록을 이용해 초기화에 필요한 정보를 전달하도록 강제하면 올바른 상태를 가진 객체의 생성을 보장할 수 있다.

## 상속과 다형성
### 컴파일 시간 의존성과 실행 시간 의존성
확장 가능한 객체지향 설계가 가지는 특징은 코드의 의존성과 실행 시점의 의존성이 다르다는 것이다. 다만 이해하기 어려워진다. 
확장 가능성과 복잡도 사이의 트레이드 오프가 필요하다. 

### 차이에 의한 프로그래밍
상속을 통해서 차이 나는 것만 따로 구현하는 방법

### 상속과 인터페이스
상속이 가치있는 이유는 재사용성 보다도 인터페이스를 물려받을 수 있기 때문이다. 
인터페이스는 객체가 이해할 수 있는 메세지의 목록이다. 

```java
public Money calculateMovieFee(Screening screening) {
    return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
```

이 코드에서 Movie는 discountPolicy를 상속받는 객체가 무엇이든 간에 calculateDiscountAmount만 실행할 수 있으면 상관없다는 의미를 가진다.
자식 클래스가 부모 클래스를 대신하는 것을 `Upcasting`이라고 한다. 

### 다형성 
다형성이란 동일한 메세지를 수신했을 때, 객체의 타입에 따라 다르게 응답할 수 있는 능력을 말한다. 
movie는 똑같은 `메세지`를 보내지만, 실행시점에 무엇을 상속하느냐에 따라서 실행되는 `메서드`는 다르다.
이를 `동적바인딩`이라고 한다. 

### 추상화와 유연성
추상화를 사용하면 좀 더 유연해진다. 
인터페이스를 하나 더 만들면 코드가 그만큼 더 복잡하고 많아지는 대신에, 더 적절한 분리를 할 수 있게 된다. 

### 상속
상속의 가장 큰 문제점은 캡슐화를 위반한다는 것이다. 상속을 구현하려면 부모의 메서드를 잘 알고 있어야 한다. 
부모가 변경되면 자식도 변경되어야 할 가능성이 높아진다. 

### 합성
DiscountInterface를 통해서 약하게 연결된다.

## 질문
flyingdoctor는 어떤가? 유연성과 복잡도 사이에 어느 위치에 있는가?
